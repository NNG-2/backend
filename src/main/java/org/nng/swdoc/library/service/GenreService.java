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

    public List<GenreDto> getAllGenres() {
        logger.info("Getting all genres");
        List<Genre> genres = genreRepository.findAll();
        List<GenreDto> genreDtos = genreMapper.toDtoList(genres);
        logger.info("Retrieved {} genres", genreDtos.size());
        return genreDtos;
    }

    public GenreDto getGenreById(Long id) {
        logger.info("Getting genre with id: {}", id);
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));
        GenreDto genreDto = genreMapper.toDto(genre);
        logger.info("Retrieved genre: {}", genreDto);
        return genreDto;
    }

    public GenreDto createGenre(GenreDto genreDto) {
        logger.info("Creating genre: {}", genreDto);
        Genre genre = genreMapper.toEntity(genreDto);
        Genre createdGenre = genreRepository.save(genre);
        GenreDto createdGenreDto = genreMapper.toDto(createdGenre);
        logger.info("Genre created: {}", createdGenreDto);
        return createdGenreDto;
    }

    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        logger.info("Updating genre with id: {}", id);
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));
        genreMapper.updateGenreFromDto(genreDto, genre);
        Genre updatedGenre = genreRepository.save(genre);
        GenreDto updatedGenreDto = genreMapper.toDto(updatedGenre);
        logger.info("Genre updated: {}", updatedGenreDto);
        return updatedGenreDto;
    }

    public void deleteGenre(Long id) {
        logger.info("Deleting genre with id: {}", id);
        genreRepository.deleteById(id);
        logger.info("Genre deleted");
    }
}
