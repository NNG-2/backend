package com.documentation.library.domains;

import com.documentation.library.dtos.ReaderDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "reader")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;

    @OneToMany(mappedBy = "reader")
    private List<Rent> rents;

    public Reader(ReaderDto readerDto) {
        this.name = readerDto.getName();
        this.address = readerDto.getAddress();
        this.phoneNumber = readerDto.getPhoneNumber();
        this.email = readerDto.getEmail();
        this.password = readerDto.getPassword();
        this.balance = 0.0;
    }
}
