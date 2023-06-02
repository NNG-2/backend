package com.documentation.library.controllers;

import com.documentation.library.domains.User;
import com.documentation.library.dtos.UserDto;
import com.documentation.library.mappers.UserMapper;
import com.documentation.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/registration")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/testget")
    @PreAuthorize("isAuthenticated()")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return "Hello, " + username + "! It's just for test.";
    }
}
