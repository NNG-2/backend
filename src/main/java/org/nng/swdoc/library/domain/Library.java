package org.nng.swdoc.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "library")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "balance")
    private Double balance;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    @OneToMany(mappedBy = "library")
    private List<User> users;
}
