package org.nng.swdoc.library.mapper;

import org.mapstruct.MappingTarget;
import org.nng.swdoc.library.domain.Author;
import org.nng.swdoc.library.dto.AuthorDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(source = "books", target = "books")
    AuthorDto toDto(Author entity);

    @InheritInverseConfiguration
    Author toEntity(AuthorDto dto);

    @Mapping(source = "books", target = "books")
    List<AuthorDto> toDtoList(List<Author> entities);

    @InheritInverseConfiguration
    List<Author> toEntityList(List<AuthorDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateAuthorFromDto(AuthorDto dto, @MappingTarget Author entity);
}
