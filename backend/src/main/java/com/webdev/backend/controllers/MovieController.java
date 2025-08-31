package com.webdev.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.webdev.backend.models.MovieResponse;
import com.webdev.backend.services.MovieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public MovieResponse getMovieList(@RequestParam String query) {
        return movieService.searchMovies(query);
    }
    
}
