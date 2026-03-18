package com.raag.movie_service.controller;

import com.raag.movie_service.model.Movie;
import com.raag.movie_service.response.Movies;
import com.raag.movie_service.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieServiceController {

    @Autowired
    private MovieService movieService;

    @Operation(
            summary = "Get all movies",
            description = "Retrieve a list of all movies currently stored in the catalog."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)))
    })
    @GetMapping
    public Page<Movie> getMovies(@RequestParam(required = false) String title, @RequestParam(required = false) String genre, @RequestParam(required = false) double minRating,
                                 @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return movieService.getMovies(title, genre, minRating, page, size);
    }

    @Operation(
            summary = "Add a new movie",
            description = "Create and store a new movie record in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public Movie addMovie(@Valid @RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @Operation(
            summary = "Update a movie by ID",
            description = "Update the title, genre, or rating of an existing movie."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public Movie updateMovie(@Parameter(description = "ID of the movie to update", required = true) @PathVariable Long id, @Valid @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @Operation(
            summary = "Delete a movie by ID",
            description = "Remove a movie record from the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @DeleteMapping("/{id}")
    public void deleteMovie(@Parameter(description = "ID of the movie to delete", required = true) @PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
