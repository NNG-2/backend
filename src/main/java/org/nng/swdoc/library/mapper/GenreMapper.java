package org.nng.swdoc.library.mapper;

import org.mapstruct.MappingTarget;
import org.nng.swdoc.library.domain.Genre;
import org.nng.swdoc.library.dto.GenreDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto toDto(Genre entity);

    @InheritInverseConfiguration
    Genre toEntity(GenreDto dto);

    List<GenreDto> toDtoList(List<Genre> entities);

    @InheritInverseConfiguration
    List<Genre> toEntityList(List<GenreDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateGenreFromDto(GenreDto dto, @MappingTarget Genre entity);
}
