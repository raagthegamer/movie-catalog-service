package com.raag.movie_service.service;

import com.raag.movie_service.exception.MovieNotFoundException;
import com.raag.movie_service.model.Movie;
import com.raag.movie_service.repository.MovieRepository;
import com.raag.movie_service.response.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movies getMovies() {
        Movies movies = new Movies();
        List<Movie> movieList =  movieRepository.findAll();
        movies.setMovies(movieList);
        return movies;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setTitle(updatedMovie.getTitle());
                    movie.setGenre(updatedMovie.getGenre());
                    movie.setRating(updatedMovie.getRating());
                    return movieRepository.save(movie);
                }).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }
}
