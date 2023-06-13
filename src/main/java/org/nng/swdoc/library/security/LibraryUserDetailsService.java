package org.nng.swdoc.library.security;

import org.nng.swdoc.library.domain.User;
import org.nng.swdoc.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            var authorities = new SimpleGrantedAuthority(user.getCategory().getName());
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of(authorities));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
