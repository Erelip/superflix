package com.ggkps.superflix.api.professional;

import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.models.SerieContent;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/professional")
public class SeriePro {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieService serieService;

    public SeriePro() {
    }

    @PostMapping("/serie/")
    public String createSerie(@RequestBody SerieContent serieContent) {
        Serie newSerie = serieService.createSerie(serieContent);

        System.out.println(newSerie);

        if (newSerie != null) {
            return newSerie.toString();
        }

        return "Serie not created";
    }

    @GetMapping("/serie/{serie_id}")
    public String readSerie(@PathVariable("serie_id") Long serie_id) {
        Optional<Serie> serie = serieRepository.findById(serie_id);

        if (serie.isPresent()) {
            return serie.get().toString();
        }

        return "Serie not found";
    }

    @PatchMapping("/serie/{serie_id}")
    public String updateSerie(@PathVariable("serie_id") long serie_id, @RequestBody SerieContent serieContent) {
        Optional<Serie> serie = serieRepository.findById(serie_id);

        if (serie.isEmpty()) {
            return "Serie not found";
        }

        Serie updatedSerie = serieService.updateSerie(serie_id, serieContent);

        return updatedSerie.toString();
    }

    @DeleteMapping("/serie/{serie_id}")
    public String deleteSerie(@PathVariable("serie_id") Long serie_id) {
        boolean exists = serieService.deleteSerie(serie_id);

        return exists ? "Serie deleted" : "Serie not found";
    }
}
