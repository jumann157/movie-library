package com.webdev.backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.webdev.backend.models.MovieDTO;
import com.webdev.backend.models.MovieResponse;
import com.webdev.backend.services.MovieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public MovieResponse getMovieList(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovieToDb(@RequestBody MovieDTO movie) {
        try {
            movieService.checkDuplicates(movie);
            // add an instance to UserMovie (getUserId, and getMovieID (not tmbdID -> get primary key))
            return ResponseEntity.ok("Movie added successfully");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Failed to add movie: " + e.getMessage());
        }
    }
    
    
}
