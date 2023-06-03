package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.dto.LibraryDto;
import org.nng.swdoc.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarie")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable Long id) {
        LibraryDto libraryDto = libraryService.getLibraryById(id);
        return ResponseEntity.ok(libraryDto);
    }

    @GetMapping
    public ResponseEntity<List<LibraryDto>> getAllLibraries() {
        List<LibraryDto> libraryDtos = libraryService.getAllLibraries();
        return ResponseEntity.ok(libraryDtos);
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary(@RequestBody LibraryDto libraryDto) {
        LibraryDto createdLibraryDto = libraryService.createLibrary(libraryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibraryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDto> updateLibrary(@PathVariable Long id, @RequestBody LibraryDto libraryDto) {
        LibraryDto updatedLibraryDto = libraryService.updateLibrary(id, libraryDto);
        return ResponseEntity.ok(updatedLibraryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }
}

