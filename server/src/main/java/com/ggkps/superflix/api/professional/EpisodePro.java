package com.ggkps.superflix.api.professional;

import com.ggkps.superflix.entities.Episode;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.models.EpisodeContent;
import com.ggkps.superflix.repositories.EpisodeRepository;
import com.ggkps.superflix.services.EpisodeService;
import com.ggkps.superflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/professional")
public class EpisodePro {

    @Autowired
    private UserService userService;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private EpisodeService episodeService;

    public EpisodePro() {
    }

    @PostMapping("/episode")
    public ResponseEntity<Episode> createEpisode(@RequestHeader(value="Authorization") String authorization, @RequestBody EpisodeContent episodeContent) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Episode newEpisode = episodeService.createEpisode(episodeContent);

        if (newEpisode != null) {
            return ResponseEntity.ok().body(newEpisode);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/episode/{episode_id}")
    public ResponseEntity<Episode> readEpisode(@RequestHeader(value="Authorization") String authorization, @PathVariable("episode_id") Long episode_id) {
        Optional<Episode> episode = episodeRepository.findById(episode_id);
        return episode.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/episode/{episode_id}")
    public ResponseEntity<Episode> updateEpisode(@RequestHeader(value="Authorization") String authorization, @PathVariable("episode_id") long episode_id, @RequestBody EpisodeContent episodeContent) {
        // Check if user is authorized to update episode
        Episode updatedEpisode = episodeService.updateEpisode(episode_id, episodeContent);

        return ResponseEntity.ok().body(updatedEpisode);
    }

    @DeleteMapping("/episode/{episode_id}")
    public ResponseEntity<Episode> deleteEpisode(@RequestHeader(value="Authorization") String authorization, @PathVariable("episode_id") Long episode_id) {
        // Check if user is authorized to delete episode
        boolean exists = episodeService.deleteEpisode(episode_id);

        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
