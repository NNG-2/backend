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
        Library library = libraryRepository.save(libraryMapper.toEntity(libraryDto));
        logger.info("CREATE library: {}", library);
        return libraryMapper.toDto(library);
    }

    public LibraryDto findById(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Library not found with id: " + id));
        logger.info("GET library: {}", library);
        return libraryMapper.toDto(library);
    }

    public List<LibraryDto> findAll() {
        List<Library> libraries = libraryRepository.findAll();
        logger.info("GET libraries: {}", libraries.size());
        return libraryMapper.toDtoList(libraries);
    }

    public LibraryDto updateLibrary(Long id, LibraryDto libraryDto) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Library not found with id: " + id));
        libraryMapper.updateLibraryFromDto(libraryDto, library);
        library = libraryRepository.save(library);
        logger.info("UPDATE library: {}", library);
        return libraryMapper.toDto(library);
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
        logger.info("DELETE library: {}", id);
    }
}

