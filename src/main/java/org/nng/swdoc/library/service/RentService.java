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
        logger.info("Creating rent: {}", rentDto);
        Rent rent = rentMapper.toEntity(rentDto);
        Rent savedRent = rentRepository.save(rent);
        logger.info("Rent successfully created with id: {}", savedRent.getId());
        return rentMapper.toDto(savedRent);
    }

    public RentDto getRentById(Long id) {
        logger.info("Getting rent with id: {}", id);
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        RentDto rentDto = rentMapper.toDto(rent);
        logger.info("Retrieved rent: {}", rentDto);
        return rentDto;
    }

    public List<RentDto> getAllRents() {
        logger.info("Getting all rents");
        List<Rent> rents = rentRepository.findAll();
        List<RentDto> rentDtos = rentMapper.toDtoList(rents);
        logger.info("Retrieved {} rents", rentDtos.size());
        return rentDtos;
    }

    public RentDto updateRent(Long id, RentDto rentDto) {
        logger.info("Updating rent with id: {}", id);
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent not found with id: " + id));
        rentMapper.updateRentFromDto(rentDto, rent);
        Rent updatedRent = rentRepository.save(rent);
        RentDto updatedRentDto = rentMapper.toDto(updatedRent);
        logger.info("Rent updated: {}", updatedRentDto);
        return updatedRentDto;
    }

    public void deleteRent(Long id) {
        logger.info("Deleting rent with id: {}", id);
        if (rentRepository.existsById(id)) {
            rentRepository.deleteById(id);
            logger.info("Rent successfully deleted with id: {}", id);
        } else {
            throw new EntityNotFoundException("Rent not found with id: " + id);
        }
    }
}


