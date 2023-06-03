package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.dto.InputUserDto;
import org.nng.swdoc.library.dto.OutputUserDto;
import org.nng.swdoc.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<User> register(@RequestBody InputUserDto inputUserDto) {
        return ResponseEntity.ok(userService.createUser(inputUserDto));
    }

    @GetMapping("/testget")
    @PreAuthorize("isAuthenticated()")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return "Hello, " + email + "! It's just for test.";
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OutputUserDto> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No such user"));

        return ResponseEntity.ok(user.toDto());
    }

}
