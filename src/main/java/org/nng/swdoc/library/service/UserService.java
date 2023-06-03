package org.nng.swdoc.library.service;

import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.dto.UserDto;
import org.nng.swdoc.library.repository.UserRepository;
import org.nng.swdoc.library.security.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) {
        try {
            User user = new User(userDto);
            user.setPassword(Encoder.encode(user.getPassword()));
            logger.info("User successfully registered");
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error creating user: " + e.getMessage());
            throw new RuntimeException("Error creating user", e);
        }
    }
}
