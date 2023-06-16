package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.domain.Rent;
import org.nng.swdoc.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookRentManager implements RentObserver {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);


    @Override
    public boolean onRentEventChange(Context ctx, Rent rent) {
        try {
            logger.info("Rent Book: " + rent.getId());

            var user = rent.getUser();
            var book = rent.getBook();
            var library = book.getLibrary();

            if (book.getQuantity() < 1) {
                throw new RuntimeException("Insufficient book quantity");
            }

            if (user.getBalance() < book.getRentalCost() + book.getCollateralCost()) {
                throw new RuntimeException("Insufficient user balance");
            }

            user.setBalance(user.getBalance() - (book.getRentalCost() + book.getCollateralCost()));
            book.setQuantity(book.getQuantity() - 1);
            library.setBalance(library.getBalance() + book.getRentalCost() + book.getCollateralCost());

            ctx.getUserService().updateUser(user);
            ctx.getBookService().updateBook(book);
            ctx.getLibraryService().updateLibrary(library);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            ctx.getRentService().deleteRent(rent.getId());
            return false;
        }
    }
}
