package org.nng.swdoc.library.domain;

import org.nng.swdoc.library.dto.InputUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nng.swdoc.library.dto.OutputUserDto;

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

    public User(InputUserDto inputUserDto) {
        this.name = inputUserDto.getName();
        this.address = inputUserDto.getAddress();
        this.phoneNumber = inputUserDto.getPhoneNumber();
        this.email = inputUserDto.getEmail();
        this.password = inputUserDto.getPassword();
        this.balance = 0.0;
    }

    public OutputUserDto toDto() {
        return new OutputUserDto(
                id, name, address, phoneNumber, email, balance,
                category == null ? null : category.getName(),
                library == null ? null : library.getName()
        );
    }
}
