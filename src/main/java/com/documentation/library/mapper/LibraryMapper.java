package com.documentation.library.mapper;

import com.documentation.library.domain.Library;
import com.documentation.library.dto.LibraryDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface LibraryMapper {
    LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);

    @Mapping(source = "books", target = "books")
    LibraryDto toDto(Library entity);

    @InheritInverseConfiguration
    Library toEntity(LibraryDto dto);

    @Mapping(source = "books", target = "books")
    List<LibraryDto> toDtoList(List<Library> entities);

    @InheritInverseConfiguration
    List<Library> toEntityList(List<LibraryDto> dtoList);
}
