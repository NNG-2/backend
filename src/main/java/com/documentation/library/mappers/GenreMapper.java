package com.documentation.library.mappers;

import com.documentation.library.domains.Genre;
import com.documentation.library.dtos.GenreDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    @Mapping(source = "books", target = "books")
    GenreDto toDto(Genre entity);

    @InheritInverseConfiguration
    Genre toEntity(GenreDto dto);

    @Mapping(source = "books", target = "books")
    List<GenreDto> toDtoList(List<Genre> entities);

    @InheritInverseConfiguration
    List<Genre> toEntityList(List<GenreDto> dtoList);
}
