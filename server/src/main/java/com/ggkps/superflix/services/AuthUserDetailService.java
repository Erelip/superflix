package com.ggkps.superflix.services;

import com.ggkps.superflix.models.AuthUser;
import com.ggkps.superflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.ggkps.superflix.entities.User> optionalUser;
        AuthUser authUser;

        try {
            optionalUser = repo.findByUsername(username);
            if(optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found");
            authUser = new AuthUser(optionalUser.get());

        } catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException(e.getMessage());
        }

        return authUser;
    }
}