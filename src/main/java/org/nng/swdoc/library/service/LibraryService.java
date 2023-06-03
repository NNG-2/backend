package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Library;
import org.nng.swdoc.library.dto.LibraryDto;
import org.nng.swdoc.library.mapper.LibraryMapper;
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

    @Autowired
    private LibraryMapper libraryMapper;

    public LibraryDto createLibrary(LibraryDto libraryDto) {
        logger.info("Creating library: {}", libraryDto);
        Library library = libraryMapper.toEntity(libraryDto);
        Library createdLibrary = libraryRepository.save(library);
        LibraryDto createdLibraryDto = libraryMapper.toDto(createdLibrary);
        logger.info("Library created: {}", createdLibraryDto);
        return createdLibraryDto;
    }

    public LibraryDto getLibraryById(Long id) {
        logger.info("Getting library with id: {}", id);
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Library not found with id: " + id));
        LibraryDto libraryDto = libraryMapper.toDto(library);
        logger.info("Retrieved library: {}", libraryDto);
        return libraryDto;
    }

    public List<LibraryDto> getAllLibraries() {
        logger.info("Getting all libraries");
        List<Library> libraries = libraryRepository.findAll();
        List<LibraryDto> libraryDtos = libraryMapper.toDtoList(libraries);
        logger.info("Retrieved {} libraries", libraryDtos.size());
        return libraryDtos;
    }

    public LibraryDto updateLibrary(Long id, LibraryDto libraryDto) {
        logger.info("Updating library with id: {}", id);
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Library not found with id: " + id));
        libraryMapper.updateLibraryFromDto(libraryDto, library);
        Library updatedLibrary = libraryRepository.save(library);
        LibraryDto updatedLibraryDto = libraryMapper.toDto(updatedLibrary);
        logger.info("Library updated: {}", updatedLibraryDto);
        return updatedLibraryDto;
    }

    public void deleteLibrary(Long id) {
        logger.info("Deleting library with id: {}", id);
        if (libraryRepository.existsById(id)) {
            libraryRepository.deleteById(id);
            logger.info("Library deleted");
        } else {
            throw new EntityNotFoundException("Library not found with id: " + id);
        }
    }
}

