package com.ggkps.superflix.controllers;

import com.ggkps.superflix.SuperflixApplication;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.models.Jwt;
import com.ggkps.superflix.models.LoginSchema;
import com.ggkps.superflix.models.RegisterSchema;
import com.ggkps.superflix.repositories.UserRepository;
import com.ggkps.superflix.services.AuthenticationService;
import com.ggkps.superflix.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtTokenUtil;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Jwt> login(@RequestBody LoginSchema loginSchema) {
        User user = this.authenticationService.authenticate(loginSchema.getUsername(), loginSchema.getPassword()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        String token = jwtTokenUtil.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null), user.getRole(), user.getEmail());
        System.out.println(token);
        return ResponseEntity.ok(new Jwt(token));
    }

    @GetMapping("/logout")
    public String logout() {
        return "Logout!";
    }

    @GetMapping("/register")
    public String register(@RequestBody RegisterSchema registerSchema) {
        List<User> allUsers = this.repository.findAll();

        System.out.println(registerSchema);
        return "Register!";
    }
}