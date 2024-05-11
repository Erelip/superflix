package com.ggkps.superflix.api.admin;

import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.models.MovieContent;
import com.ggkps.superflix.models.SerieContent;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.services.SerieService;
import com.ggkps.superflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class SerieAdmin {

    @Autowired
    private UserService userService;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieService serieService;

    public SerieAdmin() {
    }

    @PostMapping("/serie")
    public ResponseEntity<Serie> createSerie(@RequestHeader(value="Authorization") String authorization, @RequestBody SerieContent serieContent) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Serie newSerie = serieService.createSerie(serieContent, user.get());

        if (newSerie != null) {
            return ResponseEntity.ok().body(newSerie);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/serie")
    public ResponseEntity<List<Serie>> readSeries(@RequestHeader(value="Authorization") String authorization) {
        List<Serie> series = serieRepository.findAll();
        return ResponseEntity.ok().body(series);
    }

    @GetMapping("/serie/{serie_id}")
    public ResponseEntity<Serie> readSerie(@RequestHeader(value="Authorization") String authorization, @PathVariable("serie_id") Long serie_id) {
        Optional<Serie> serie = serieRepository.findById(serie_id);
        return serie.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/serie/{serie_id}")
    public ResponseEntity<Serie> updateSerie(@RequestHeader(value="Authorization") String authorization, @PathVariable("serie_id") long serie_id, @RequestBody SerieContent serieContent) {
        Serie updatedSerie = serieService.updateSerie(serie_id, serieContent);

        return ResponseEntity.ok().body(updatedSerie);
    }

    @DeleteMapping("/serie/{serie_id}")
    public ResponseEntity<Serie> deleteSerie(@RequestHeader(value="Authorization") String authorization, @PathVariable("serie_id") Long serie_id) {
        boolean exists = serieService.deleteSerie(serie_id);

        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
