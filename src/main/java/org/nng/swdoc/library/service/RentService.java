package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.dto.InputRentDto;
import org.nng.swdoc.library.dto.OutputRentDto;
import org.nng.swdoc.library.mapper.RentMapper;
import org.nng.swdoc.library.repository.RentRepository;
import org.nng.swdoc.library.domain.Rent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    private static final Logger logger = LoggerFactory.getLogger(RentService.class);

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RentMapper rentMapper;

    public OutputRentDto createRent(InputRentDto inputRentDto) {
        Rent rent = rentRepository.save(rentMapper.toEntity(inputRentDto));
        logger.info("CRATE rent: {}", inputRentDto);
        return rentMapper.toDto(rent);
    }

    public OutputRentDto getRentById(Long id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        logger.info("GET rent: {}", rentMapper.toDto(rent));
        return rentMapper.toDto(rent);
    }

    public List<OutputRentDto> getAllRents() {
        List<Rent> rents = rentRepository.findAll();
        logger.info("GET rents: {}", rents.size());
        return rentMapper.toDtoList(rents);
    }

    public OutputRentDto updateRent(Long id, InputRentDto inputRentDto) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        rentMapper.updateRentFromDto(inputRentDto, rent);
        rent = rentRepository.save(rent);
        logger.info("UPDATE rent: {}", inputRentDto);
        return rentMapper.toDto(rent);
    }

    public OutputRentDto updateRent(Long id, OutputRentDto outputRentDto) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        rentMapper.updateRentFromDto(outputRentDto, rent);
        rent = rentRepository.save(rent);
        logger.info("UPDATE rent: {}", outputRentDto);
        return rentMapper.toDto(rent);
    }

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
        logger.info("DELETE rent: {}", id);
    }
}


