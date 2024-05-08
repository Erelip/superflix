package com.ggkps.superflix.api.admin;

import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.models.SerieContent;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.repositories.UserRepository;
import com.ggkps.superflix.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class UserAdmin {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SerieService serieService;

    @Autowired
    private SerieRepository serieRepository;

    // @Autowired
    // private UserService userService;

    public UserAdmin() {
    }

    /*
    @PostMapping("/user/")
    public String createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);

        System.out.println(newUser);

        if (newUser != null) {
            return newUser.toString();
        }

        return "User not created";
    }
    */

    @GetMapping("/user/{serie_id}")
    public String readSerie(@PathVariable("serie_id") Long serie_id) {
        Optional<User> serie = userRepository.findById(serie_id);

        if (serie.isPresent()) {
            return serie.get().toString();
        }

        return "Serie not found";
    }

    @PatchMapping("/user/{serie_id}")
    public String updateSerie(@PathVariable("serie_id") long serie_id, @RequestBody SerieContent serieContent) {
        Optional<Serie> serie = serieRepository.findById(serie_id);

        if (serie.isEmpty()) {
            return "Serie not found";
        }

        Serie updatedSerie = serieService.updateSerie(serie_id, serieContent);

        return updatedSerie.toString();
    }

    @DeleteMapping("/user/{serie_id}")
    public String deleteSerie(@PathVariable("serie_id") Long serie_id) {
        boolean exists = serieService.deleteSerie(serie_id);

        return exists ? "Serie deleted" : "Serie not found";
    }
}
