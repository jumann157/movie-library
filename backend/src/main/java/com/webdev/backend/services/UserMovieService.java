package com.webdev.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.webdev.backend.entity.Movie;
import com.webdev.backend.entity.User;
import com.webdev.backend.entity.UserMovies;
import com.webdev.backend.models.MovieLibraryDTO;
import com.webdev.backend.repository.UserMovieRepository;

@Service
public class UserMovieService {
    private UserMovieRepository userMovieRepo;

    public UserMovieService(UserMovieRepository userMovieRepo) {
        this.userMovieRepo = userMovieRepo;
    }

    public void addUserMovie(User u, Movie m) {
        UserMovies userMovie = new UserMovies(u, m);
        userMovieRepo.save(userMovie); 
    }

    public boolean checkUserMovieDuplicates(User u, Movie m) {
        List<UserMovies> movies = userMovieRepo.findByUser(u); 
        for (UserMovies umv : movies) {
            System.out.println(umv.getMovie().getTitle());

            Movie userMovie = umv.getMovie();
            if(userMovie.equals(m)) {
                return true; // duplicate is found, user already has movie added to their library
            }
        }
        addUserMovie(u, m); // if not found (movie is NOT added to user's library), add a record
        return false; // m is not found
    }

    public List<MovieLibraryDTO> getUserLibrary(User user) {
        List<UserMovies> userMovies = userMovieRepo.findByUser(user);

        List<MovieLibraryDTO> movieLibrary = new ArrayList<>();
        for(UserMovies um : userMovies) {
            movieLibrary.add(convertTMovieLibraryDTO(um));
        }
        return movieLibrary;
    }

    public MovieLibraryDTO convertTMovieLibraryDTO(UserMovies userMovie) {
        return new MovieLibraryDTO(
            userMovie.getMovie().getTmdbId(),
            userMovie.getMovie().getPosterPath(),
            userMovie.getMovie().getTitle()
            );
    }
}
