package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.exception.RentExistsException;
import org.nng.swdoc.library.mangement.BookRentManager;
import org.nng.swdoc.library.mangement.BookReturnManager;
import org.nng.swdoc.library.mangement.Context;
import org.nng.swdoc.library.mangement.RentObservable;
import org.nng.swdoc.library.repository.RentRepository;
import org.nng.swdoc.library.domain.Rent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RentService extends RentObservable {
    private static final Logger logger = LoggerFactory.getLogger(RentService.class);

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookService bookService;

    public RentService() {
        this.addRentEventListener(Event.BOOK_RENTED, new BookRentManager());
        this.addRentEventListener(Event.BOOK_RETURNED, new BookReturnManager());
    }

    public Rent createRent(Rent newRent) {
        var rents = rentRepository.getRentsByUserId(newRent.getUser().getId());

        if (rents.stream().anyMatch(r -> Objects.equals(r.getBook().getId(), newRent.getBook().getId()))) {
            logger.info("CRATE rent FAILED: {}", newRent.getId());
            throw new RentExistsException();
        }

        newRent.setIsReturned(false);
        newRent.setIsDamaged(false);

        Rent rent = rentRepository.save(newRent);
        this.notifyRentEventChange(
                Context.builder()
                        .rentService(this)
                        .userService(userService)
                        .bookService(bookService)
                        .libraryService(libraryService)
                        .build(),
                Event.BOOK_RENTED, rent
        );
        logger.info("CRATE rent: {}", rent.getId());
        return rent;
    }

    public void returnBook(Rent rent) {
        this.notifyRentEventChange(Context.builder()
                .rentService(this)
                .userService(userService)
                .bookService(bookService)
                .libraryService(libraryService)
                .build(),
                Event.BOOK_RETURNED, rent
        );
        logger.info("RETURN rent: {}", rent.getId());
    }

    public Rent findById(Long id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        logger.info("GET rent: {}", rent.getId());
        return rent;
    }

    public List<Rent> findByUserId(Long userId) {
        List<Rent> rents = rentRepository.getRentsByUserId(userId);
        logger.info("GET rents: {}", rents.size());
        return rents;
    }

    public Rent getRentByBookIdAndUserId(Long bookId, Long userId) {
        var rents = rentRepository.getRentByBookIdAndUserId(bookId, userId).stream().filter(rent -> !rent.getIsReturned()).toList();
        if (rents.isEmpty()) {
            throw new EntityNotFoundException("Rent not found for this book and user");
        }
        var rent = rents.get(0);
        logger.info("GET rent: {}", rent.getId());
        return rent;
    }

    public List<Rent> getAllRents() {
        List<Rent> rents = rentRepository.findAll();
        logger.info("GET rents: {}", rents.size());
        return rents;
    }

    public void updateRent(Rent rent) {
        logger.info("UPDATE rents: {}", rent.getId());
        rentRepository.save(rent);
    }

    public void deleteRent(Long id) {
        logger.info("DELETE rents: {}", id);
        rentRepository.deleteById(id);
    }
}


