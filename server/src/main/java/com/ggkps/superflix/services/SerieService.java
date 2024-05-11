package com.ggkps.superflix.services;

import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.entities.VisualContent;
import com.ggkps.superflix.models.SerieContent;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.repositories.VisualContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private VisualContentRepository visualContentRepository;

    public SerieService() {
    }

    public Serie createSerie(SerieContent serieContent, User user) {
        VisualContent visualContent = new VisualContent()
                .setTitle(serieContent.getTitle())
                .setReleaseAt(serieContent.getReleaseAt() != null ? serieContent.getReleaseAt() : new Date())
                .setCategory(serieContent.getCategory())
                .setCreator(serieContent.getCreator())
                .setDescription(serieContent.getDescription())
                .setUser(user);

        Serie newSerie = new Serie(serieContent.getNumberOfSeasons(), visualContent);
        visualContentRepository.save(visualContent);
        return serieRepository.save(newSerie);
    }

    public Serie updateSerie(long id, SerieContent serieContent) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isEmpty()) {
            return null;
        }

        VisualContent visualContent = serie.get().getVisualContent();
        if (serieContent.getTitle() != null) visualContent.setTitle(serieContent.getTitle());
        if (serieContent.getReleaseAt() != null) visualContent.setReleaseAt(serieContent.getReleaseAt());
        if (serieContent.getCategory() != null) visualContent.setCategory(serieContent.getCategory());
        if (serieContent.getCreator() != null) visualContent.setCreator(serieContent.getCreator());
        if (serieContent.getDescription() != null) visualContent.setDescription(serieContent.getDescription());
        if (serieContent.getNumberOfSeasons() != null) serie.get().setNumberOfSeasons(serieContent.getNumberOfSeasons());

        visualContentRepository.save(visualContent);
        return serieRepository.save(serie.get());
    }

    public boolean deleteSerie(long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            visualContentRepository.deleteById(serie.get().getVisualContent().getId());
            serieRepository.deleteById(id);
            serieRepository.flush();
            return true;
        }
        return false;
    }
}
