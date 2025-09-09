package com.webdev.backend.services;

import com.webdev.backend.models.MovieResponse;
import com.webdev.backend.models.MovieDTO;
import com.webdev.backend.entity.Movie;
import com.webdev.backend.repository.MovieRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieService {

    private final WebClient webClient;
    private MovieRepository movieRepo;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public MovieService(MovieRepository movieRepo) {
        this.webClient = WebClient.builder()
                        .baseUrl("https://api.themoviedb.org/3/")
                        .build();
        this.movieRepo = movieRepo;
    }

    // searches for movie based on given title
    public MovieResponse searchMovies(String query) {
        return webClient.get().uri("/search/movie?api_key={apiKey}&query={query}", apiKey, query)
                        .retrieve()
                        .bodyToMono(MovieResponse.class)
                        .block();
    }

    private Movie convertToEntity(MovieDTO movie) {
        int movieId = movie.getId();
        String movieTitle = movie.getTitle();
        String movieOverview = movie.getOverview();
        String moviePosterPath = movie.getPoster();
        LocalDate movieReleaseDate = LocalDate.parse(movie.getReleaseDate());
        Movie movieRecord = new Movie(movieId, movieTitle, movieOverview, moviePosterPath, movieReleaseDate);
        return movieRecord;
    }

    // saves movie to database (if searched through the api)
    public void addMovie(MovieDTO movie) { //parameter is supposed to be DTO
        Movie movieRecord = convertToEntity(movie); // turn it to movieRecord (entity)
        movieRepo.save(movieRecord); // save the entity here
    }

    // checks for movie duplicates from movies added through the api 
    public void checkDuplicates(MovieDTO movie) {
        int movieId = movie.getId(); //tmdb_id
        Optional<Movie> m = movieRepo.findByTmdbId(movieId); // returns movie enity
        // if movie is not in database, add it
        if(m.isEmpty()) { addMovie(movie); } 
        // add an instance to UserMovie (getUserId, and getMovieID (not tmbdID -> get primary key))
    }

    // Movie should exist
    public Movie getMovie(int tmbdId) {
        return movieRepo.findByTmdbId(tmbdId).orElseThrow();
    }
}
