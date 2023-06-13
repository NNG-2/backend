package org.nng.swdoc.library.mapper;

import org.mapstruct.MappingTarget;
import org.nng.swdoc.library.domain.Book;
import org.nng.swdoc.library.dto.BookDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class, LibraryMapper.class, ImageMapper.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "image.id", target = "imageId")
    BookDto toDto(Book entity);

    @InheritInverseConfiguration
    Book toEntity(BookDto dto);

    @Mapping(source = "image.id", target = "imageId")
    List<BookDto> toDtoList(List<Book> entities);

    @InheritInverseConfiguration
    List<Book> toEntityList(List<BookDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rents", ignore = true)
    void updateBookFromDto(BookDto dto, @MappingTarget Book entity);
}
