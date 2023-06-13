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

import java.time.LocalDateTime;

@Component
public class BookReturnManager implements RentObserver {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private static final int EARLY_CASHBACK_PERCENTAGE = 2;
    private static final int ELDERLY_CASHBACK_PERCENTAGE = 5;
    private static final int STUDENT_CASHBACK_PERCENTAGE = 5;
    private static final int OVERDUE_FINE_PERCENTAGE = 2;

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
            logger.info("Return Book: " + rent.toString());

            var user = userService.findById(rent.getUserId());
            var book = bookService.findById(rent.getBookId());
            var library = libraryService.findById(book.getLibrary().getId());

            book.setQuantity(book.getQuantity() + 1);
            rent.setIsReturned(true);

            if (!rent.getIsDamaged()) {
                user.setBalance(user.getBalance() + book.getCollateralCost());
                library.setBalance(library.getBalance() - book.getCollateralCost());
            }

            int totalCashbackPercentage = 0;
            int totalFinePercentage = 0;

            switch (user.getCategory().getName()) {
                case "USER" -> {
                    totalCashbackPercentage = STUDENT_CASHBACK_PERCENTAGE;
                    totalFinePercentage = STUDENT_CASHBACK_PERCENTAGE;
                }
                case "ELDERLY" -> {
                    totalCashbackPercentage = ELDERLY_CASHBACK_PERCENTAGE;
                    totalFinePercentage = ELDERLY_CASHBACK_PERCENTAGE;
                }
                case "STUDENT" -> {
                    totalCashbackPercentage = EARLY_CASHBACK_PERCENTAGE;
                    totalFinePercentage = EARLY_CASHBACK_PERCENTAGE;
                }
            }

            double totalCashbackAmount = ((double) totalCashbackPercentage / 100) * book.getRentalCost();
            double totalFineAmount = ((double) totalFinePercentage / 100) * book.getRentalCost();

            if (LocalDateTime.now().toLocalDate().isBefore(rent.getExpiredDate())) {
                library.setBalance(library.getBalance() - totalCashbackAmount);
                user.setBalance(user.getBalance() + totalCashbackAmount);
            } else {
                library.setBalance(library.getBalance() + totalFineAmount);
                user.setBalance(user.getBalance() - totalFineAmount);
            }

            userService.updateUser(user.getId(), user);
            bookService.updateBook(book.getId(), book);
            libraryService.updateLibrary(library.getId(), library);
            rentService.updateRent(rent.getId(), rent);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
