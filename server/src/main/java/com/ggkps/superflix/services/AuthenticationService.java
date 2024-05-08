package com.ggkps.superflix.services;

import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.entities.VisualContent;
import com.ggkps.superflix.models.MovieContent;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.repositories.UserRepository;
import com.ggkps.superflix.repositories.VisualContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public AuthenticationService() {
    }

    public Optional<User> getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
