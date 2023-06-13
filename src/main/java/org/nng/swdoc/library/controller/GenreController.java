package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.dto.GenreDto;
import org.nng.swdoc.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
@CrossOrigin
@EnableMethodSecurity(securedEnabled = true)
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id) {
        GenreDto genreDto = genreService.getGenreById(id);
        return ResponseEntity.ok(genreDto);
    }

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        List<GenreDto> genreDtos = genreService.getAllGenres();
        return ResponseEntity.ok(genreDtos);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto) {
        GenreDto createdGenreDto = genreService.createGenre(genreDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenreDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        GenreDto updatedGenreDto = genreService.updateGenre(id, genreDto);
        return ResponseEntity.ok(updatedGenreDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}

