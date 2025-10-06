package com.raag.movie_service.response;

import com.raag.movie_service.model.Movie;

import java.util.List;

public class Movies {
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
