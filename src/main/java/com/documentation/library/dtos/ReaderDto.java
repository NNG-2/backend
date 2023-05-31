package com.documentation.library.dtos;

import com.documentation.library.domains.Category;
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
public class ReaderDto {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String password;

    private Double balance;

    private Category category;

    private Library library;

    private List<Rent> rents;
}
