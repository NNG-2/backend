package com.documentation.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderDto {

    private Long id;

    private String name;

    private Long categoryId;

    private Long libraryId;
}
