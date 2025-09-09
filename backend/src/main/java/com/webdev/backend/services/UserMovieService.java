package com.webdev.backend.services;

import org.springframework.stereotype.Service;

import com.webdev.backend.entity.Movie;
import com.webdev.backend.entity.User;
import com.webdev.backend.entity.UserMovies;
import com.webdev.backend.repository.UserMovieRepository;

@Service
public class UserMovieService {
    private UserMovieRepository userMovieRepo;
    private UserService userService;
    private MovieService movieService;

    public UserMovieService(UserMovieRepository userMovieRepo, UserService userService, MovieService movieService) {
        this.userMovieRepo = userMovieRepo;
        this.userService = userService;
        this.movieService = movieService;
    }

    public void addUserMovie(User u, Movie m) {
        UserMovies userMovie = new UserMovies(u, m);
        userMovieRepo.save(userMovie); 
    }

    // 1. get user, 2. get all movies associated with said user, 3. check if movie exists 
    // 4. if exists, return true 5. if not, return false and call addUserMovie
    // private boolean checkDuplicates() {

    // }
}
