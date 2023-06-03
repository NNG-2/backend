package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Author;
import org.nng.swdoc.library.dto.AuthorDto;
import org.nng.swdoc.library.mapper.AuthorMapper;
import org.nng.swdoc.library.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public AuthorDto createAuthor(AuthorDto authorDto) {
        logger.info("Creating author: {}", authorDto);
        Author author = authorMapper.toEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        AuthorDto savedAuthorDto = authorMapper.toDto(savedAuthor);
        logger.info("Author created: {}", savedAuthorDto);
        return savedAuthorDto;
    }

    public AuthorDto getAuthorById(Long id) {
        logger.info("Getting author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        AuthorDto authorDto = authorMapper.toDto(author);
        logger.info("Retrieved author: {}", authorDto);
        return authorDto;
    }

    public List<AuthorDto> getAllAuthors() {
        logger.info("Getting all authors");
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> authorDtos = authors.stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Retrieved {} authors", authorDtos.size());
        return authorDtos;
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        logger.info("Updating author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        Author updatedAuthor = authorRepository.save(author);
        AuthorDto updatedAuthorDto = authorMapper.toDto(updatedAuthor);
        logger.info("Author updated: {}", updatedAuthorDto);
        return updatedAuthorDto;
    }

    public void deleteAuthor(Long id) {
        logger.info("Deleting author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        authorRepository.delete(author);
        logger.info("Author deleted");
    }
}

