package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nng.swdoc.library.domain.Book;
import org.nng.swdoc.library.domain.Rent;
import org.nng.swdoc.library.service.BookService;
import org.nng.swdoc.library.service.UserService;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentDto {
    private LocalDate issueDate;
    private LocalDate expiredDate;
    private Long bookId;
    private Long userId;

    public Rent toEntity(BookService bookService, UserService userService) {
        return Rent.builder()
              .issueDate(issueDate)
              .expiredDate(expiredDate)
              .book(bookService.findById(bookId))
              .user(userService.findById(userId))
              .build();
    }
}
