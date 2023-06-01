package com.documentation.library.mappers;

import com.documentation.library.domains.Reader;
import com.documentation.library.dtos.ReaderDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LibraryMapper.class})
public interface ReaderMapper {
    ReaderMapper INSTANCE = Mappers.getMapper(ReaderMapper.class);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "library.id", target = "libraryId")
    ReaderDto toDto(Reader entity);

    @InheritInverseConfiguration
    Reader toEntity(ReaderDto dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "library.id", target = "libraryId")
    List<ReaderDto> toDtoList(List<Reader> entities);

    @InheritInverseConfiguration
    List<Reader> toEntityList(List<ReaderDto> dtoList);
}
