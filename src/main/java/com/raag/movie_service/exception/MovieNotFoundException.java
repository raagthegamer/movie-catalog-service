package com.raag.movie_service.exception;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(Long id) {
        super("Movie with ID " + id + " not found");
    }
}
