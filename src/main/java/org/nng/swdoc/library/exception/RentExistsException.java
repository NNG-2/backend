package org.nng.swdoc.library.exception;

public class RentExistsException extends RuntimeException {
    public RentExistsException() {
        super("Rent already exists for this book");
    }
}
