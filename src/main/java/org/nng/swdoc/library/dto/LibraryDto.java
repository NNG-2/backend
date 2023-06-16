package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nng.swdoc.library.domain.Library;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {
    private Long id;
    private String name;

    private Double balance;
    public Library toEntity() {
        return Library.builder()
              .id(id)
              .name(name)
              .balance(balance)
              .build();
    }
}
