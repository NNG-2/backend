package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.dto.OutputRentDto;

public interface RentObserver {
    boolean onRentEventChange(OutputRentDto rent);
}
