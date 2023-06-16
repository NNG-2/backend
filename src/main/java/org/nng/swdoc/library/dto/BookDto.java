package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nng.swdoc.library.domain.Book;
import org.nng.swdoc.library.service.AuthorService;
import org.nng.swdoc.library.service.GenreService;
import org.nng.swdoc.library.service.LibraryService;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private String title;
    private Integer quantity;
    private Double collateralCost;
    private Double rentalCost;
    private Long authorId;
    private Long genreId;
    private Long libraryId;

    public Book toEntity(AuthorService authorService, GenreService genreService, LibraryService libraryService) {
        return Book.builder()
              .title(title)
              .quantity(quantity)
              .collateralCost(collateralCost)
              .rentalCost(rentalCost)
              .author(authorService.findById(authorId))
              .genre(genreService.findById(genreId))
              .library(libraryService.findById(libraryId))
              .build();
    }
}
