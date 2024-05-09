package com.ggkps.superflix.api.user;

import com.ggkps.superflix.entities.Season;
import com.ggkps.superflix.repositories.SeasonRepository;
import com.ggkps.superflix.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class SeasonUser {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeasonService seasonService;

    public SeasonUser() {
    }

    @GetMapping("/season/{season_id}")
    public String readSeason(@PathVariable("season_id") Long season_id) {
        Optional<Season> season = seasonRepository.findById(season_id);

        if (season.isPresent()) {
            return season.get().toString();
        }

        return "Season not found";
    }
}
