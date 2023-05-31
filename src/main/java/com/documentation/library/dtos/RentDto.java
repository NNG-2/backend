package com.documentation.library.dtos;

import com.documentation.library.domains.Book;
import com.documentation.library.domains.Reader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentDto {

    private Long id;

    private LocalDate issueDate;

    private LocalDate expiredDate;

    private Book book;

    private Reader reader;
}
