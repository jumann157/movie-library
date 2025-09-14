package com.webdev.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.webdev.backend.entity.User;
import com.webdev.backend.models.MovieDTO;
import com.webdev.backend.models.MovieLibraryDTO;
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
            movieService.checkDuplicates(movie); // checks if movie already exists in Movie Entity, if not it adds it
            // checks if movie already exists in UserMovie Entity, if not it adds it
            boolean isDuplicate = userMovieService.checkUserMovieDuplicates(userService.getUser("abc123"), movieService.getMovie(movie.getId()));
            if(isDuplicate) {
                return ResponseEntity.status(400).body("UserMovie already exists");
            }
            return ResponseEntity.ok("Movie added successfully");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Failed to add movie: " + e.getMessage());
        }
    }

    @GetMapping("/library")
    public List<MovieLibraryDTO> loadLibrary() {
        User user = userService.getUser("abc123");
        return userMovieService.getUserLibrary(user);
    }
    
}
