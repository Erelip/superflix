package com.ggkps.superflix.services;

import com.ggkps.superflix.entities.Season;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.repositories.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SeasonService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    public SeasonService() {
    }

    public Season createSeason(Season season) {
        return seasonRepository.save(season);
    }

    public Season updateSeason(long id, Season season) {
        Optional<Season> updateSeason = seasonRepository.findById(id);

        if (updateSeason.isEmpty()) {
            return null;
        }

        Season seasonToUpdate = updateSeason.get();
        seasonToUpdate.setNumberOfEpisodes(season.getNumberOfEpisodes());

        return seasonRepository.save(seasonToUpdate);
    }

    public boolean deleteSeason(long id) {
        Optional<Season> season = seasonRepository.findById(id);

        if (season.isPresent()) {
            seasonRepository.deleteById(id);
            seasonRepository.flush();
            return true;
        }
        return false;
    }
}
