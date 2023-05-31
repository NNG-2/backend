package com.documentation.library.dtos;

import com.documentation.library.domains.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {

    private Long id;

    private String name;

    private String surname;

    private List<Book> books;
}
