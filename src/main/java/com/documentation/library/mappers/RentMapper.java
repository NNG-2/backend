package com.documentation.library.mappers;

import com.documentation.library.domains.Rent;
import com.documentation.library.dtos.RentDto;
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
}
