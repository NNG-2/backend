package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.service.CategoryService;
import org.nng.swdoc.library.service.LibraryService;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private Long categoryId;
    private Long libraryId;

    public User toEntity(CategoryService categoryService, LibraryService libraryService) {
        return User.builder()
              .name(name)
              .address(address)
              .phoneNumber(phoneNumber)
              .balance(100.0)
              .email(email)
              .password(password)
              .category(categoryService.findById(categoryId))
              .library(libraryService.findById(libraryId))
              .build();
    }

}
