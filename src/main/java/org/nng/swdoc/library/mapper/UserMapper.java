package org.nng.swdoc.library.mapper;

import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.dto.InputUserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.nng.swdoc.library.dto.OutputUserDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LibraryMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "library.id", target = "libraryId")
    OutputUserDto toDto(User entity);

    @InheritInverseConfiguration
    User toEntity(InputUserDto dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "library.id", target = "libraryId")
    List<OutputUserDto> toDtoList(List<User> entities);

    @InheritInverseConfiguration
    List<User> toEntityList(List<InputUserDto> dtoList);
}
