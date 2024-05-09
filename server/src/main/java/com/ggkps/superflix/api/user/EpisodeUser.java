package com.ggkps.superflix.api.user;

import com.ggkps.superflix.entities.Episode;
import com.ggkps.superflix.models.EpisodeContent;
import com.ggkps.superflix.repositories.EpisodeRepository;
import com.ggkps.superflix.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class EpisodeUser {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeService episodeService;

    public EpisodeUser() {
    }

    @GetMapping("/episode/{episode_id}")
    public String readEpisode(@PathVariable("episode_id") Long episode_id) {
        Optional<Episode> episode = episodeRepository.findById(episode_id);

        if (episode.isPresent()) {
            return episode.get().toString();
        }

        return "Episode not found";
    }
}
