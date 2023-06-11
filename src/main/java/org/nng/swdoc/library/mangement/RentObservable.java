package org.nng.swdoc.library.mangement;

import org.nng.swdoc.library.domain.Rent;
import org.nng.swdoc.library.dto.RentDto;

import java.util.ArrayList;
import java.util.List;

public interface RentObservable {
    List<RentObserver> observers = new ArrayList<RentObserver>();

    default void addObserver(RentObserver observer) {
        observers.add(observer);
    }

    default void removeObserver(RentObserver observer) {
        observers.remove(observer);
    }

    default void sendRent(RentDto rent) {
        for (RentObserver observer : observers) {
            observer.updateRent(rent);
        }
    }
}
