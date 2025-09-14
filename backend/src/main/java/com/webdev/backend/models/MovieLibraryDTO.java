package com.webdev.backend.models;

public class MovieLibraryDTO {
    private int tmdbId;
    private String posterPath;

    public MovieLibraryDTO(int tmdbId, String posterPath) {
        this.tmdbId = tmdbId;
        this.posterPath =  posterPath;
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
}
