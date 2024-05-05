package com.ggkps.superflix.controllers;

import com.ggkps.superflix.SuperflixApplication;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class HomeController {

    @Autowired
    private UserRepository repository;

    private final Logger logger = LoggerFactory.getLogger(SuperflixApplication.class);

    @GetMapping("/user/mock/add")
    public String index() {
        List<User> allUsers = this.repository.findAll();
        logger.info("Number of customers: " + allUsers.size());

        User newUser = new User();
        newUser.setFirstname("John");
        newUser.setLastname("Doe");
        newUser.setEmail("john.doe@gmail.com");
        newUser.setPassword("password");
        logger.info("Saving new customer...");
        this.repository.save(newUser);

        allUsers = this.repository.findAll();
        logger.info("Number of customers: " + allUsers.size());
        return "Greetings from Spring Boot!";
    }
}