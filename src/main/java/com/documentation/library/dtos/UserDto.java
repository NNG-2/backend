package com.documentation.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String password;

    private Double balance;

    private Long categoryId;

    private Long libraryId;

}
