package org.nng.swdoc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {

    private Long id;

    private String name;

    private List<BookDto> books;
}
