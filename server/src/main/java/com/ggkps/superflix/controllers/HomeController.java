package com.ggkps.superflix.controllers;

import com.ggkps.superflix.SuperflixApplication;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/home")

public class HomeController {

    @Autowired
    private UserRepository repository;

    private final Logger logger = LoggerFactory.getLogger(SuperflixApplication.class);

    @GetMapping("/")
    public String home() {
        return "Welcome to Superflix!";
    }
}