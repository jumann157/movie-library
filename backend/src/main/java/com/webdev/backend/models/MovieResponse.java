package com.webdev.backend.models;

import java.util.List;

public class MovieResponse {
    private List<Movie> movieList;
    private int page; 
    private int total_results;

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
