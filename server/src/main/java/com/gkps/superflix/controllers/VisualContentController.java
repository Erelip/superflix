package com.gkps.superflix.controllers;

import com.gkps.superflix.SuperflixApplication;
import com.gkps.superflix.entities.User;
import com.gkps.superflix.entities.VisualContent;
import com.gkps.superflix.repositories.UserRepository;
import com.gkps.superflix.repositories.VisualContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Date;


@RestController
public class VisualContentController {

    @Autowired
    private VisualContentRepository repository;

    private final Logger logger = LoggerFactory.getLogger(SuperflixApplication.class);

    @GetMapping("/visual_content/mock/add")
    public String index() {
        List<VisualContent> allVisualContent = this.repository.findAll();
        logger.info("Number of customers: " + allVisualContent.size());

        VisualContent newVisualContent = new VisualContent();
        newVisualContent.setTitle("Zen Emission");
        newVisualContent.setDescription(
                "Zen c'est le late-show présenté par Maxime Biaggi et Grimkujow. " +
                "Des invités, de l'humour et des happenings.");
        newVisualContent.setCategory("Talk Show");
        newVisualContent.setCreator("Maxime Biaggi");
        newVisualContent.setReleaseAt(new Date());

        logger.info("Saving new visual content...");
        this.repository.save(newVisualContent);

        allVisualContent = this.repository.findAll();
        logger.info("Number of visual content: " + allVisualContent.size());
        return newVisualContent.toString();
    }
}