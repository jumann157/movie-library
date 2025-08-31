package com.webdev.backend.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieResponse {

    @JsonProperty("results")
    private List<Movie> movieList;
    private int page; 
    private int total_results;

    public MovieResponse() {
        // Empty constructor for Jackson
    }

    public MovieResponse(List<Movie> movieList, int page, int total_results) {
        this.movieList = movieList;
        this.page = page;
        this.total_results = total_results;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }
}
