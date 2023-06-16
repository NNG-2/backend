package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Author;
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

    public Author createAuthor(Author newAuthor) {
        Author author = authorRepository.save(newAuthor);
        logger.info("CREATE author: {}", author.getId());
        return author;
    }

    public Author findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        logger.info("GET author: {}", author.getId());
        return author;
    }

    public List<Author> findAll() {
        List<Author> authors = authorRepository.findAll();
        logger.info("GET authors: {}", authors.size());
        return authors;
    }
}

