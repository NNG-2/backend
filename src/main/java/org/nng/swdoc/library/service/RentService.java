package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.mapper.RentMapper;
import org.nng.swdoc.library.repository.RentRepository;
import org.nng.swdoc.library.domain.Rent;
import org.nng.swdoc.library.dto.RentDto;
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

    public RentDto createRent(RentDto rentDto) {
        Rent rent = rentRepository.save(rentMapper.toEntity(rentDto));
        logger.info("CRATE rent: {}", rent);
        return rentMapper.toDto(rent);
    }

    public RentDto getRentById(Long id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        logger.info("GET rent: {}", rent);
        return rentMapper.toDto(rent);
    }

    public List<RentDto> getAllRents() {
        List<Rent> rents = rentRepository.findAll();
        logger.info("GET rents: {}", rents.size());
        return rentMapper.toDtoList(rents);
    }

    public RentDto updateRent(Long id, RentDto rentDto) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        rentMapper.updateRentFromDto(rentDto, rent);
        rent = rentRepository.save(rent);
        logger.info("UPDATE rent: {}", rent);
        return rentMapper.toDto(rent);
    }

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
        logger.info("DELETE rent: {}", id);
    }
}


