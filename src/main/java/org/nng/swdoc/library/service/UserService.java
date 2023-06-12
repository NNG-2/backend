package org.nng.swdoc.library.service;

import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.dto.InputUserDto;
import org.nng.swdoc.library.mapper.UserMapper;
import org.nng.swdoc.library.repository.UserRepository;
import org.nng.swdoc.library.security.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(InputUserDto inputUserDto) {
        try {
            User user = userMapper.toEntity(inputUserDto);
            System.out.println(user.toString());
            user.setPassword(Encoder.getInstance().encode(user.getPassword()));
            logger.info("User successfully registered");
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error creating user: " + e.getMessage());
            throw new RuntimeException("Error creating user", e);
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Long id, User newUser) {
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
