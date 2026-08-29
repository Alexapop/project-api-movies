package dev.ioana.apimovies.mapper;

import java.util.HashSet;
import java.util.Set;
import dev.ioana.apimovies.dto.MovieRequestDTO;
import dev.ioana.apimovies.dto.MovieResponseDTO;
import dev.ioana.apimovies.entity.ActorEntity;
import dev.ioana.apimovies.entity.GenreEntity;
import dev.ioana.apimovies.entity.MovieEntity;
import dev.ioana.apimovies.entity.YearEntity;

public class MovieMapper {

    public static MovieEntity toEntity(MovieRequestDTO request,
            YearEntity year,
            Set<GenreEntity> genres,
            Set<ActorEntity> actors) {

        MovieEntity movie = new MovieEntity(request.title());

        movie.setYear(year);
        movie.setGenres(genres);
        movie.setActors(actors);

        return movie;
    }

    public static MovieResponseDTO toResponseDTO(MovieEntity movie) {
        Long id = movie.getId();
        String title = movie.getTitle();
        Integer releaseYear = null;

        if (movie.getYear() != null) {
            releaseYear = movie.getYear().getReleaseYear();
        }

        Set<String> genres = new HashSet<>();

        for (GenreEntity genre : movie.getGenres()) {
            genres.add(genre.getName());
        }

        Set<String> actors = new HashSet<>();

        for (ActorEntity actor : movie.getActors()) {
            actors.add(actor.getNameActor());
        }

        return new MovieResponseDTO(
                id,
                title,
                releaseYear,
                genres,
                actors);
    }
}
