package com.documentation.library.dtos;

import com.documentation.library.domains.Book;
import com.documentation.library.domains.Reader;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {

    private Long id;

    private String name;

    private Double balance;

    private List<Book> books;

    private List<Reader> readers;
}
