package com.ggkps.superflix.api;

import com.ggkps.superflix.entities.Season;
import com.ggkps.superflix.repositories.SeasonRepository;
import com.ggkps.superflix.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SeasonCRUD {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeasonService seasonService;

    public SeasonCRUD() {
    }

    @PostMapping("/season/")
    public String createSeason(@RequestBody Season season) {
        Season newSeason = seasonService.createSeason(season);

        System.out.println(newSeason);

        if (newSeason != null) {
            return newSeason.toString();
        }

        return "Season not created";
    }

    @GetMapping("/season/{season_id}")
    public String readSeason(@PathVariable("season_id") Long season_id) {
        Optional<Season> season = seasonRepository.findById(season_id);

        if (season.isPresent()) {
            return season.get().toString();
        }

        return "Season not found";
    }

    @PatchMapping("/season/{season_id}")
    public String updateSeason(@PathVariable("season_id") long season_id, @RequestBody Season season) {
        Optional<Season> updateSeason = seasonRepository.findById(season_id);

        if (updateSeason.isEmpty()) {
            return "Season not found";
        }

        Season updatedSeason = seasonService.updateSeason(season_id, updateSeason.get());

        return updatedSeason.toString();
    }

    @DeleteMapping("/season/{season_id}")
    public String deleteSeason(@PathVariable("season_id") Long season_id) {
        boolean exists = seasonService.deleteSeason(season_id);

        return exists ? "Season deleted" : "Season not found";
    }
}
