package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Genre;
import org.nng.swdoc.library.dto.GenreDto;
import org.nng.swdoc.library.mapper.GenreMapper;
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

    @Autowired
    private GenreMapper genreMapper;

    public GenreDto createGenre(GenreDto genreDto) {
        Genre genre = genreRepository.save(genreMapper.toEntity(genreDto));
        logger.info("CREATE genre: {}", genre);
        return genreMapper.toDto(genre);
    }

    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));
        logger.info("GET genre: {}", genre);
        return genreMapper.toDto(genre);
    }

    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        logger.info("GET genres: {}", genres.size());
        return genreMapper.toDtoList(genres);
    }

    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));
        genreMapper.updateGenreFromDto(genreDto, genre);
        genre = genreRepository.save(genre);
        logger.info("UPDATE genre: {}", genre);
        return genreMapper.toDto(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
        logger.info("DELETE genre: {}", id);
    }
}
