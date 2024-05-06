package com.ggkps.superflix.api;

import com.ggkps.superflix.entities.Episode;
import com.ggkps.superflix.models.EpisodeContent;
import com.ggkps.superflix.repositories.EpisodeRepository;
import com.ggkps.superflix.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EpisodeCRUD {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeService episodeService;

    public EpisodeCRUD() {
    }

    @PostMapping("/episode/")
    public String createEpisode(@RequestBody EpisodeContent episodeContent) {
        Episode newEpisode = episodeService.createEpisode(episodeContent);

        if (newEpisode != null) {
            return newEpisode.toString();
        }

        return "Episode not created";
    }

    @GetMapping("/episode/{episode_id}")
    public String readEpisode(@PathVariable("episode_id") Long episode_id) {
        Optional<Episode> episode = episodeRepository.findById(episode_id);

        if (episode.isPresent()) {
            return episode.get().toString();
        }

        return "Episode not found";
    }

    @PatchMapping("/episode/{episode_id}")
    public String updateEpisode(@PathVariable("episode_id") long episode_id, @RequestBody EpisodeContent episodeContent) {
        Optional<Episode> updateEpisode = episodeRepository.findById(episode_id);

        if (updateEpisode.isEmpty()) {
            return "Episode not found";
        }

        Episode updatedEpisode = episodeService.updateEpisode(episode_id, episodeContent);

        return updatedEpisode.toString();
    }

    @DeleteMapping("/episode/{episode_id}")
    public String deleteEpisode(@PathVariable("episode_id") long episode_id) {
        boolean deleted = episodeService.deleteEpisode(episode_id);

        if (deleted) {
            return "Episode deleted";
        }

        return "Episode not found";
    }
}
