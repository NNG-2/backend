package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputUserDto {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private Double balance;

    private String category;

    private String library;

}
