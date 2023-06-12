package org.nng.swdoc.library.mapper;

import org.mapstruct.MappingTarget;
import org.nng.swdoc.library.domain.Rent;
import org.nng.swdoc.library.dto.InputRentDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.nng.swdoc.library.dto.OutputRentDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface RentMapper {
    RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    OutputRentDto toDto(Rent entity);

    @InheritInverseConfiguration
    Rent toEntity(InputRentDto dto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    List<OutputRentDto> toDtoList(List<Rent> entities);

    @InheritInverseConfiguration
    List<Rent> toEntityList(List<InputRentDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "issueDate", ignore = true)
    void updateRentFromDto(OutputRentDto dto, @MappingTarget Rent entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "issueDate", ignore = true)
    void updateRentFromDto(InputRentDto dto, @MappingTarget Rent entity);
}
