package com.documentation.library.mappers;

import com.documentation.library.domains.Author;
import com.documentation.library.dtos.AuthorDto;
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
}
