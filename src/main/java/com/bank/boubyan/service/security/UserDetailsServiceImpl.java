package com.bank.boubyan.service.security;

import com.bank.boubyan.exception.UserNotFoundException;
import com.bank.boubyan.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // The username is the subject = the email
        var userOptional = userRepository.findByEmail(username);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User with username = " + username + " Is not found", HttpStatus.NOT_FOUND.value());
    }
}
