package com.webdev.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    @JsonProperty("original_title")
    private String name;
    @JsonProperty("poster_path")
    private String poster;

    public Movie() {
        // Empty constructor for Jackson
    }

    public Movie(String name, String poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }
}
