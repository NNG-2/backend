package org.nng.swdoc.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "title", unique = true)
    private String title;

    @Basic
    @Column(name = "quantity")
    private Integer quantity;

    @Basic
    @Column(name = "collateral_cost")
    private Double collateralCost;

    @Basic
    @Column(name = "rental_cost")
    private Double rentalCost;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToMany(mappedBy = "book")
    private List<Rent> rents;
}
