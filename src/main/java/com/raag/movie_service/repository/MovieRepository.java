package com.raag.movie_service.repository;

import com.raag.movie_service.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Dynamic finder for filtering by genre and minimum rating
    Page<Movie> findByGenreContainingIgnoreCaseAndRatingGreaterThanEqual(
            String genre, double rating, Pageable pageable);

    // Optional: filter by title substring
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
