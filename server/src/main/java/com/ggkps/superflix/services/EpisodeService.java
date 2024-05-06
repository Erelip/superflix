package com.ggkps.superflix.services;

import com.ggkps.superflix.entities.Episode;
import com.ggkps.superflix.entities.Season;
import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.models.EpisodeContent;
import com.ggkps.superflix.repositories.EpisodeRepository;
import com.ggkps.superflix.repositories.SeasonRepository;
import com.ggkps.superflix.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SerieRepository serieRepository;

    public EpisodeService() {
    }

    public Episode createEpisode(EpisodeContent episodeContent) {
        Optional<Serie> serie = serieRepository.findById(episodeContent.getSerieId());

        if (serie.isEmpty()) {
            return null;
        }

        Optional<Season> season = seasonRepository.findBySerieIdAndNumber(
                serie.get().getId(),
                episodeContent.getSeasonNumber()
        );

        if (season.isEmpty()) {
            return null;
        }

        Episode newEpisode = new Episode(
                episodeContent.getNumber(),
                episodeContent.getPath(),
                episodeContent.getDuration(),
                season.get()
        );
        return episodeRepository.save(newEpisode);
    }

    public Episode updateEpisode(long id, EpisodeContent episodeContent) {
        Optional<Episode> episode = episodeRepository.findById(id);

        if (episode.isEmpty()) {
            return null;
        }

        Episode updateEpisode = episode.get();
        if (episodeContent.getNumber() != null) updateEpisode.setNumber(episodeContent.getNumber());
        if (episodeContent.getPath() != null) updateEpisode.setPath(episodeContent.getPath());
        if (episodeContent.getDuration() != null) updateEpisode.setDuration(episodeContent.getDuration());

        return episodeRepository.save(updateEpisode);
    }

    public boolean deleteEpisode(long id) {
        Optional<Episode> episode = episodeRepository.findById(id);

        if (episode.isPresent()) {
            episodeRepository.deleteById(id);
            episodeRepository.flush();
            return true;
        }
        return false;
    }
}