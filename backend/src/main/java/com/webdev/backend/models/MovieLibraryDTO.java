package com.webdev.backend.models;

public class MovieLibraryDTO {
    private int tmdbId;
    private String posterPath;
    private String movieTitle;

    public MovieLibraryDTO(int tmdbId, String posterPath, String movieTitle) {
        this.tmdbId = tmdbId;
        this.posterPath =  posterPath;
        this.movieTitle = movieTitle;
    }

    public int getTmdbId() {
        return tmdbId;
    }
    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
