package org.nng.swdoc.library.service;

import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.dto.UserDto;
import org.nng.swdoc.library.repository.UserRepository;
import org.nng.swdoc.library.security.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;


    public User createUser(User newUser) {
        try {
            System.out.println(newUser.toString());
            newUser.setPassword(Encoder.getInstance().encode(newUser.getPassword()));
            logger.info("User successfully registered");
            return userRepository.save(newUser);
        } catch (Exception e) {
            logger.error("Error creating user: " + e.getMessage());
            throw new RuntimeException("Error creating user", e);
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User newUser) {
        User user = userRepository.findById(newUser.getId())
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }
}
