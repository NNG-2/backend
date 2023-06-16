package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nng.swdoc.library.domain.Author;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    private String name;
    private String surname;

    public Author toEntity() {
        return Author.builder()
              .name(name)
              .surname(surname)
              .build();
    }
}
