package com.webdev.backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.webdev.backend.models.MovieDTO;
import com.webdev.backend.models.MovieResponse;
import com.webdev.backend.services.MovieService;
import com.webdev.backend.services.UserMovieService;
import com.webdev.backend.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;
    private final UserMovieService userMovieService;
    private final UserService userService;

    public MovieController(MovieService movieService, UserMovieService userMovieService, UserService userService) {
        this.movieService = movieService;
        this.userMovieService = userMovieService;
        this.userService = userService;
    }

    @GetMapping("/search")
    public MovieResponse getMovieList(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovieToDb(@RequestBody MovieDTO movie) {
        try {
            movieService.checkDuplicates(movie); // checks Movie Entity
            userMovieService.addUserMovie(userService.getUser("abc123"), movieService.getMovie(movie.getId()));
            // add an instance to UserMovie (getUserId, and getMovieID (not tmbdID -> get primary key))
            /*
             * 1. create a UserMovieService instance
             * 2. if(checkduplicates) is true, return ResponseEntity with success message
             * 3. if false, return ResponseEntity with error message
             * 4. update front-end
             */
            return ResponseEntity.ok("Movie added successfully");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Failed to add movie: " + e.getMessage());
        }
    }
    
    
}
