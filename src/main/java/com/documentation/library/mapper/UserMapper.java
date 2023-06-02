package com.documentation.library.mapper;

import com.documentation.library.domain.User;
import com.documentation.library.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, LibraryMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "library.id", target = "libraryId")
    UserDto toDto(User entity);

    @InheritInverseConfiguration
    User toEntity(UserDto dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "library.id", target = "libraryId")
    List<UserDto> toDtoList(List<User> entities);

    @InheritInverseConfiguration
    List<User> toEntityList(List<UserDto> dtoList);
}
