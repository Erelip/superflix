package com.ggkps.superflix.api.admin;

import com.ggkps.superflix.entities.Season;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.models.SeasonContent;
import com.ggkps.superflix.repositories.SeasonRepository;
import com.ggkps.superflix.services.SeasonService;
import com.ggkps.superflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class SeasonAdmin {

    @Autowired
    private UserService userService;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeasonService seasonService;

    public SeasonAdmin() {
    }

    @PostMapping("/season")
    public ResponseEntity<Season> createSeason(@RequestHeader(value="Authorization") String authorization, @RequestBody SeasonContent seasonContent) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Season newSeason = seasonService.createSeason(seasonContent);

        if (newSeason != null) {
            return ResponseEntity.ok().body(newSeason);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/season")
    public ResponseEntity<List<Season>> readSeasons(@RequestHeader(value="Authorization") String authorization) {
        List<Season> seasons = seasonRepository.findAll();
        return ResponseEntity.ok().body(seasons);
    }

    @GetMapping("/season/{season_id}")
    public ResponseEntity<Season> readSeason(@RequestHeader(value="Authorization") String authorization, @PathVariable("season_id") Long season_id) {
        Optional<Season> season = seasonRepository.findById(season_id);
        return season.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/season/{season_id}")
    public ResponseEntity<Season> updateSeason(@RequestHeader(value="Authorization") String authorization, @PathVariable("season_id") long season_id, @RequestBody SeasonContent seasonContent) {
        Season updatedSeason = seasonService.updateSeason(season_id, seasonContent);

        return ResponseEntity.ok().body(updatedSeason);
    }

    @DeleteMapping("/season/{season_id}")
    public ResponseEntity<Season> deleteSeason(@RequestHeader(value="Authorization") String authorization, @PathVariable("season_id") Long season_id) {
        boolean exists = seasonService.deleteSeason(season_id);

        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
