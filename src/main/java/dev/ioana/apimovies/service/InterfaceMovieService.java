package dev.ioana.apimovies.service;

import java.util.List;

import dev.ioana.apimovies.dto.MovieRequestDTO;
import dev.ioana.apimovies.dto.MovieResponseDTO;

public interface InterfaceMovieService {

    List<MovieResponseDTO> getAllMovies();

    MovieResponseDTO getMovieById(Long id);

    MovieResponseDTO createMovie(MovieRequestDTO request);

    MovieResponseDTO updateMovie(
            Long id,
            MovieRequestDTO request);

    void deleteMovie(Long id);

    List<MovieResponseDTO> findMoviesByTitle(String title);

    List<MovieResponseDTO> findMoviesByGenre(String genre);

}
