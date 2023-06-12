package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.dto.RentDto;

public interface RentObserver {
    void updateRent(RentDto rent);
}
