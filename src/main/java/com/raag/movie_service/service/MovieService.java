package com.raag.movie_service.service;

import com.raag.movie_service.exception.MovieNotFoundException;
import com.raag.movie_service.model.Movie;
import com.raag.movie_service.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> getMovies(String title, String genre, Double minRating, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        if (title != null && !title.isEmpty()) {
            return movieRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else if (genre != null && minRating != null) {
            return movieRepository.findByGenreContainingIgnoreCaseAndRatingGreaterThanEqual(
                    genre, minRating, pageable);
        } else {
            return movieRepository.findAll(pageable);
        }
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
