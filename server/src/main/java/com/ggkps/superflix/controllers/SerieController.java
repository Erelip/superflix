package com.ggkps.superflix.controllers;

import com.ggkps.superflix.SuperflixApplication;
import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.entities.VisualContent;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.repositories.VisualContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private VisualContentRepository visualContentRepository;
    private final Logger logger = LoggerFactory.getLogger(SuperflixApplication.class);

    @GetMapping("/serie/mock/add")
    public String index() {

        VisualContent allVisualContent = this.visualContentRepository.findOneByTitle("Zen Emission");
        List<Serie> allSerie = this.serieRepository.findAll();
        logger.info("Number of series: " + allSerie.size());

        Serie newSerie = new Serie();
        newSerie.setNumberOfSeasons(3);
        newSerie.setVisualContent(allVisualContent);

        logger.info("Saving new visual content...");
        this.serieRepository.save(newSerie);

        allSerie = this.serieRepository.findAll();
        logger.info("Number of visual content: " + allSerie.size());
        return newSerie.toString();
    }
}