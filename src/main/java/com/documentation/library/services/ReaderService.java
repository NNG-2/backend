package com.documentation.library.services;

import com.documentation.library.domains.Reader;
import com.documentation.library.dtos.ReaderDto;
import com.documentation.library.repositories.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {
    private static final Logger logger = LoggerFactory.getLogger(ReaderService.class);
    @Autowired
    private ReaderRepository readerRepository;

    public Reader createReader(ReaderDto readerDto) {
        try {
            Reader reader = new Reader(readerDto);
            logger.info("Reader successfully registered");
            return readerRepository.save(reader);
        } catch (Exception e) {
            logger.error("Error creating reader: " + e.getMessage());
            throw new RuntimeException("Error creating reader", e);
        }
    }
}
