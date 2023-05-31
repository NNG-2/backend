package com.documentation.library.dtos;

import com.documentation.library.domains.Author;
import com.documentation.library.domains.Genre;
import com.documentation.library.domains.Library;
import com.documentation.library.domains.Rent;
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
public class BookDto {
    private Long id;

    private String title;

    private Double quantity;

    private Double collateralCost;

    private Double rentalCost;

    private Author author;

    private Genre genre;

    private Library library;

    private List<Rent> rents;
}
