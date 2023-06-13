package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.dto.OutputRentDto;
import org.nng.swdoc.library.service.BookService;
import org.nng.swdoc.library.service.LibraryService;
import org.nng.swdoc.library.service.RentService;
import org.nng.swdoc.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookRentManager implements RentObserver {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private RentService rentService;


    @Override
    public boolean onRentEventChange(OutputRentDto rent) {
        try {
            logger.info("Rent Book: " + rent.toString());

            var user = userService.findById(rent.getUserId());
            var book = bookService.findById(rent.getBookId());
            var library = libraryService.findById(book.getLibrary().getId());

            if (book.getQuantity() < 1) {
                throw new RuntimeException("Insufficient book quantity");
            }

            if (user.getBalance() < book.getRentalCost() + book.getCollateralCost()) {
                throw new RuntimeException("Insufficient user balance");
            }

            user.setBalance(user.getBalance() - (book.getRentalCost() + book.getCollateralCost()));
            book.setQuantity(book.getQuantity() - 1);
            library.setBalance(library.getBalance() + book.getRentalCost() + book.getCollateralCost());

            userService.updateUser(user.getId(), user);
            bookService.updateBook(book.getId(), book);
            libraryService.updateLibrary(library.getId(), library);

            return true;
        } catch (Exception e) {
            rentService.deleteRent(rent.getId());
            return false;
        }
    }
}
