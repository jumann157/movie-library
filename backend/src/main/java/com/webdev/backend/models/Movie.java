package com.webdev.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    @JsonProperty("original_title")
    private String name;
    @JsonProperty("poster_path")
    private String poster;
    @JsonProperty("release_date")
    private String releaseDate;

    public Movie() {
        // Empty constructor for Jackson
    }

    public Movie(String name, String poster, String releaseDate) {
        this.name = name;
        this.poster = poster;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
