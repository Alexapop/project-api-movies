package dev.ioana.apimovies.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ioana.apimovies.dto.MovieRequestDTO;
import dev.ioana.apimovies.dto.MovieResponseDTO;
import dev.ioana.apimovies.service.InterfaceMovieService;

@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {

    private final InterfaceMovieService movieService;

    public MovieController(InterfaceMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieResponseDTO getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);

    }

    @PostMapping
    public MovieResponseDTO createMovie(
            @RequestBody MovieRequestDTO request) {
        return movieService.createMovie(request);
    }

    @PutMapping("/{id}")
    public MovieResponseDTO updateMovie(
            @PathVariable Long id,
            @RequestBody MovieRequestDTO request) {
        return movieService.updateMovie(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/search/title")
    public List<MovieResponseDTO> findMoviesByTitle(@RequestParam String title) {
        return movieService.findMoviesByTitle(title);
    }

    @GetMapping("/search/genre")
    public List<MovieResponseDTO> findMoviesByGenre(@RequestParam String genre) {
        return movieService.findMoviesByGenre(genre);
    }

}