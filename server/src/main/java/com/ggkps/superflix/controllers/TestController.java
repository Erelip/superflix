package com.ggkps.superflix.controllers;

import com.ggkps.superflix.SuperflixApplication;
import com.ggkps.superflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/home")

public class TestController {

    @GetMapping("/")
    public String index() {
        return "Welcome to Superflix!";
    }
}