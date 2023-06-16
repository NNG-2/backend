package org.nng.swdoc.library.repository;

import org.nng.swdoc.library.domain.Book;
import org.nng.swdoc.library.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> getRentsByUserId(Long userId);

    List<Rent> getRentByBookIdAndUserId(Long bookId, Long userId);
}
