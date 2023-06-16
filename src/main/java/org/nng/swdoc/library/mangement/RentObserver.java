package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.domain.Rent;

public interface RentObserver {
    boolean onRentEventChange(Context ctx, Rent rent);
}
