package dev.ioana.apimovies.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.ioana.apimovies.dto.MovieRequestDTO;
import dev.ioana.apimovies.dto.MovieResponseDTO;
import dev.ioana.apimovies.entity.ActorEntity;
import dev.ioana.apimovies.entity.GenreEntity;
import dev.ioana.apimovies.entity.MovieEntity;
import dev.ioana.apimovies.entity.YearEntity;
import dev.ioana.apimovies.mapper.MovieMapper;
import dev.ioana.apimovies.repository.ActorRepository;
import dev.ioana.apimovies.repository.GenreRepository;
import dev.ioana.apimovies.repository.MovieRepository;
import dev.ioana.apimovies.repository.YearRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieServiceImpl implements InterfaceMovieService {

    private final MovieRepository movieRepository;
    private final YearRepository yearRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    public MovieServiceImpl(
            MovieRepository movieRepository,
            YearRepository yearRepository,
            GenreRepository genreRepository,
            ActorRepository actorRepository) {

        this.movieRepository = movieRepository;
        this.yearRepository = yearRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public List<MovieResponseDTO> getAllMovies() {
        return toResponseDTOs(movieRepository.findAll());
    }

    @Override
    public MovieResponseDTO getMovieById(Long id) {
        return MovieMapper.toResponseDTO(getMovieEntityById(id));
    }

    @Override
    public MovieResponseDTO createMovie(MovieRequestDTO request) {
        YearEntity year = null;

        if (request.releaseYear() != null) {
            year = getOrCreateYear(request.releaseYear());
        }

        Set<GenreEntity> genres = getOrCreateGenres(request.genres());
        Set<ActorEntity> actors = getOrCreateActors(request.actors());

        MovieEntity movie = MovieMapper.toEntity(request, year, genres, actors);

        MovieEntity savedMovie = movieRepository.save(movie);

        return MovieMapper.toResponseDTO(savedMovie);
    }

    @Override
    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO request) {

        MovieEntity movie = getMovieEntityById(id);
        YearEntity year = null;

        if (request.releaseYear() != null) {
            year = getOrCreateYear(request.releaseYear());
        }

        Set<GenreEntity> genres = getOrCreateGenres(request.genres());
        Set<ActorEntity> actors = getOrCreateActors(request.actors());

        movie.setTitle(request.title());
        movie.setYear(year);
        movie.setGenres(genres);
        movie.setActors(actors);

        MovieEntity updatedMovie = movieRepository.save(movie);

        return MovieMapper.toResponseDTO(updatedMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.delete(getMovieEntityById(id));
    }

    @Override
    public List<MovieResponseDTO> findMoviesByTitle(String title) {
        return toResponseDTOs(movieRepository.findByTitleIgnoreCase(title));
    }

    @Override
    public List<MovieResponseDTO> findMoviesByGenre(String genre) {
        return toResponseDTOs(
                movieRepository.findDistinctByGenres_NameIgnoreCase(genre));
    }

    private MovieEntity getMovieEntityById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Movie not found with id: " + id));
    }

    private List<MovieResponseDTO> toResponseDTOs(List<MovieEntity> movies) {

        List<MovieResponseDTO> responses = new ArrayList<>();

        for (MovieEntity movie : movies) {
            responses.add(MovieMapper.toResponseDTO(movie));
        }

        return responses;
    }

    private YearEntity getOrCreateYear(Integer releaseYear) {
        List<YearEntity> years = yearRepository.findByReleaseYear(releaseYear);

        if (!years.isEmpty()) {
            return years.get(0);
        }

        return yearRepository.save(new YearEntity(releaseYear));
    }

    private Set<GenreEntity> getOrCreateGenres(Set<String> genreNames) {
        Set<GenreEntity> genres = new HashSet<>();

        if (genreNames == null) {
            return genres;
        }

        for (String genreName : genreNames) {
            if (genreName == null || genreName.isBlank()) {
                continue;
            }

            List<GenreEntity> existingGenres = genreRepository.findByNameIgnoreCase(genreName);

            if (!existingGenres.isEmpty()) {
                genres.add(existingGenres.get(0));
            } else {
                genres.add(genreRepository.save(new GenreEntity(genreName)));
            }
        }

        return genres;
    }

    private Set<ActorEntity> getOrCreateActors(Set<String> actorNames) {
        Set<ActorEntity> actors = new HashSet<>();

        if (actorNames == null) {
            return actors;
        }

        for (String actorName : actorNames) {
            if (actorName == null || actorName.isBlank()) {
                continue;
            }

            List<ActorEntity> existingActors = actorRepository.findByNameActorIgnoreCase(actorName);

            if (!existingActors.isEmpty()) {
                actors.add(existingActors.get(0));
            } else {
                actors.add(actorRepository.save(new ActorEntity(actorName)));
            }
        }

        return actors;
    }
}
