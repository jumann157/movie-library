package com.webdev.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.webdev.backend.models.MovieResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MovieController {

    private MovieResponse movieResponse;

    @GetMapping("/search")
    public MovieResponse getMovieList(@RequestParam String param) {
    }
    
}
