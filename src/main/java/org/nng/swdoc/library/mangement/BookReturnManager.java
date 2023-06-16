package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.domain.Rent;
import org.nng.swdoc.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class BookReturnManager implements RentObserver {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private static final int EARLY_CASHBACK_PERCENTAGE = 2;
    private static final int ELDERLY_CASHBACK_PERCENTAGE = 5;
    private static final int STUDENT_CASHBACK_PERCENTAGE = 5;
    private static final int OVERDUE_FINE_PERCENTAGE = 2;

    @Override
    public boolean onRentEventChange(Context ctx, Rent rent) {
        try {
            logger.info("Return Book: " + rent.getId());

            var user = rent.getUser();
            var book = rent.getBook();
            var library = book.getLibrary();

            book.setQuantity(book.getQuantity() + 1);
            rent.setIsReturned(true);

            if (!rent.getIsDamaged()) {
                user.setBalance(user.getBalance() + book.getCollateralCost());
                library.setBalance(library.getBalance() - book.getCollateralCost());
            }

            int totalCashbackPercentage = 0;

            switch (user.getCategory().getName()) {
                case "USER" -> {
                    totalCashbackPercentage = STUDENT_CASHBACK_PERCENTAGE;
                }
                case "ELDERLY" -> {
                    totalCashbackPercentage = ELDERLY_CASHBACK_PERCENTAGE;
                }
                case "STUDENT" -> {
                    totalCashbackPercentage = EARLY_CASHBACK_PERCENTAGE;
                }
            }

            double totalCashbackAmount = ((double) totalCashbackPercentage / 100) * book.getRentalCost();
            double totalFineAmount = ((double) OVERDUE_FINE_PERCENTAGE / 100) * book.getRentalCost();

            if (LocalDateTime.now().toLocalDate().isBefore(rent.getExpiredDate())) {
                if (!rent.getIsDamaged()) {
                    library.setBalance(library.getBalance() - totalCashbackAmount);
                    user.setBalance(user.getBalance() + totalCashbackAmount);
                }
            } else {
                library.setBalance(library.getBalance() + totalFineAmount);
                user.setBalance(user.getBalance() - totalFineAmount);
            }

            book.setQuantity(book.getQuantity() + 1);

            ctx.getUserService().updateUser(user);
            ctx.getBookService().updateBook(book);
            ctx.getLibraryService().updateLibrary(library);
            ctx.getRentService().updateRent(rent);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
