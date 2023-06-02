package com.documentation.library.controllers;

import com.documentation.library.domains.Reader;
import com.documentation.library.dtos.ReaderDto;
import com.documentation.library.mappers.ReaderMapper;
import com.documentation.library.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderMapper readerMapper;

    @PostMapping("/registration")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Reader> register(@RequestBody ReaderDto readerDto) {
        return ResponseEntity.ok(readerService.createReader(readerDto));
    }

    @GetMapping("/testget")
    @PreAuthorize("isAuthenticated()")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return "Hello, " + username + "! It's just for test.";
    }
}
