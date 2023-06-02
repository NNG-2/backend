package com.documentation.library.security;

import com.documentation.library.domains.Reader;
import com.documentation.library.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryUserDetailsService implements UserDetailsService {
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var authorities = new SimpleGrantedAuthority("user");
        Reader reader = readerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Reader not found with email: " + email));
        return new User(reader.getEmail(), reader.getPassword(), List.of(authorities));
    }
}
