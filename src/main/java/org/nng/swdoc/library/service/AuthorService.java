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
        Author author = authorRepository.save(authorMapper.toEntity(authorDto));
        logger.info("CREATE author: {}", authorDto);
        return authorMapper.toDto(author);
    }

    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        logger.info("GET author: {}", authorMapper.toDto(author));
        return authorMapper.toDto(author);
    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        logger.info("GET authors: {}", authors.size());
        return authorMapper.toDtoList(authors);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        authorMapper.updateAuthorFromDto(authorDto, author);
        author = authorRepository.save(author);
        logger.info("UPDATE author: {}", authorDto);
        return authorMapper.toDto(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
        logger.info("DELETE author: {}", id);
    }
}

