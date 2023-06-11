package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.dto.RentDto;
import org.nng.swdoc.library.service.BookService;
import org.nng.swdoc.library.service.LibraryService;
import org.nng.swdoc.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RentManger implements RentObserver {
    @Autowired
    private UserService userService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookService bookService;

    @Override
    public void updateRent(RentDto rent) {
        System.out.println("RentManger::updateRent");
        var user = userService.findById(rent.getUserId()).orElseThrow(() -> new UsernameNotFoundException("zalupa"));
        var book = bookService.findById(rent.getBookId());
        var library = libraryService.findById(book.getLibraryId());
        user.setBalance(user.getBalance() - book.getRentalCost());
        library.setBalance(library.getBalance() + book.getRentalCost());
        userService.saveUser(user);
        libraryService.updateLibrary(library.getId(), library);
    }
}
