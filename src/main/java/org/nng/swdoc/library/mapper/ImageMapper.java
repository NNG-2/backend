package org.nng.swdoc.library.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.nng.swdoc.library.domain.Image;
import org.nng.swdoc.library.dto.ImageDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto toDto(Image entity);

    @InheritInverseConfiguration
    Image toEntity(ImageDto dto);

    List<ImageDto> toDtoList(List<Image> entities);

    @InheritInverseConfiguration
    List<Image> toEntityList(List<ImageDto> dtoList);

    @Mapping(target = "id", ignore = true)
    void updateImageFromDto(ImageDto dto, @MappingTarget Image entity);
}
