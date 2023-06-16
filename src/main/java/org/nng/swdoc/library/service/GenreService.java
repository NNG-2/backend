package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Genre;
import org.nng.swdoc.library.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    private GenreRepository genreRepository;

    public Genre createGenre(Genre newGenre) {
        Genre genre = genreRepository.save(newGenre);
        logger.info("CREATE genre: {}", genre.getId());
        return genre;
    }

    public Genre findById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));
        logger.info("GET genre: {}", genre.getId());
        return genre;
    }

    public List<Genre> findAll() {
        List<Genre> genres = genreRepository.findAll();
        logger.info("GET genres: {}", genres.size());
        return genres;
    }
}
