package com.documentation.library.controllers;

import com.documentation.library.domains.Reader;
import com.documentation.library.dtos.TestReaderDto;
import com.documentation.library.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ReaderController {
    @Autowired
    private ReaderRepository readerRepository;
    @PostMapping("/registration")
    @PreAuthorize("isAnonymous()")
    public Reader register(@RequestBody TestReaderDto testReaderDto) {
        Reader reader = new Reader();
        reader.setId(testReaderDto.getId());
        reader.setEmail(testReaderDto.getEmail());
        reader.setName(testReaderDto.getName());
        reader.setPassword(testReaderDto.getPassword());
        reader.setAddress(testReaderDto.getAddress());
        reader.setPhoneNumber(testReaderDto.getPhoneNumber());
        System.out.println(testReaderDto);
        return readerRepository.save(reader);
    }

    @GetMapping("/testget")
    @PreAuthorize("permitAll()")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return "Hello, " + username + "! It's just for test.";
    }
}
