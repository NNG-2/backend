package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.dto.InputUserDto;
import org.nng.swdoc.library.dto.OutputUserDto;
import org.nng.swdoc.library.mapper.UserMapper;
import org.nng.swdoc.library.service.CategoryService;
import org.nng.swdoc.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@EnableMethodSecurity(securedEnabled = true)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<OutputUserDto> register(@RequestBody InputUserDto inputUserDto) {
        if (inputUserDto.getCategoryId() == null) {
            inputUserDto.setCategoryId(categoryService.getCategoryByName("USER").getId());
        }
        if (categoryService.getCategoryById(inputUserDto.getCategoryId()).getName().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(userService.createUser(inputUserDto)));
    }

    @GetMapping("/me")
    public ResponseEntity<OutputUserDto> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findByEmail(email);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<OutputUserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.findById(id)));
    }
}
