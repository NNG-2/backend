package com.documentation.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestReaderDto {
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String password;
}
