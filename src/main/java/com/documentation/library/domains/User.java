package com.documentation.library.domains;

import com.documentation.library.dtos.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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

    @OneToMany(mappedBy = "user")
    private List<Rent> rents;

    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.address = userDto.getAddress();
        this.phoneNumber = userDto.getPhoneNumber();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.balance = 0.0;
    }
}
