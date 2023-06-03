package org.nng.swdoc.library.mapper;

import org.mapstruct.MappingTarget;
import org.nng.swdoc.library.domain.Rent;
import org.nng.swdoc.library.dto.RentDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface RentMapper {
    RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    RentDto toDto(Rent entity);

    @InheritInverseConfiguration
    Rent toEntity(RentDto dto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    List<RentDto> toDtoList(List<Rent> entities);

    @InheritInverseConfiguration
    List<Rent> toEntityList(List<RentDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "issueDate", ignore = true)
    void updateRentFromDto(RentDto dto, @MappingTarget Rent entity);
}
