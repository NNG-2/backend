package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Library;
import org.nng.swdoc.library.dto.LibraryDto;
import org.nng.swdoc.library.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);

    @Autowired
    private LibraryRepository libraryRepository;

    public Library createLibrary(Library newLibrary) {
        Library library = libraryRepository.save(newLibrary);
        logger.info("CREATE library: {}", library.getId());
        return library;
    }

    public Library findById(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Library not found with id: " + id));
        logger.info("GET library: {}", library.getId());
        return library;
    }

    public List<Library> findAll() {
        List<Library> libraries = libraryRepository.findAll();
        logger.info("GET libraries: {}", libraries.size());
        return libraries;
    }

    public Library updateLibrary(Library library) {
        libraryRepository.save(library);
        logger.info("UPDATE library: {}", library.getId());
        return library;
    }
}

