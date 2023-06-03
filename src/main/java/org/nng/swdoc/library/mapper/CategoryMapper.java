package org.nng.swdoc.library.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.nng.swdoc.library.domain.Category;
import org.nng.swdoc.library.dto.CategoryDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(Category entity);

    @InheritInverseConfiguration
    Category toEntity(CategoryDto dto);

    List<CategoryDto> toDtoList(List<Category> entities);

    @InheritInverseConfiguration
    List<Category> toEntityList(List<CategoryDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    void updateCategoryFromDto(CategoryDto dto, @MappingTarget Category entity);
}
