package com.ggkps.superflix.api.user;

import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.models.SerieContent;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class SerieUser {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieService serieService;

    public SerieUser() {
    }

    @GetMapping("/serie/{serie_id}")
    public String readSerie(@PathVariable("serie_id") Long serie_id) {
        Optional<Serie> serie = serieRepository.findById(serie_id);

        if (serie.isPresent()) {
            return serie.get().toString();
        }

        return "Serie not found";
    }
}
