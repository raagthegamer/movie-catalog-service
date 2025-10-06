package com.raag.movie_service.controller;

import com.raag.movie_service.model.Movie;
import com.raag.movie_service.response.Movies;
import com.raag.movie_service.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieServiceController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public Movies getMovies() {
        return movieService.getMovies();
    }

    @PostMapping
    public Movie addMovie(@Valid @RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
