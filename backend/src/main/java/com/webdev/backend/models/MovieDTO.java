package com.webdev.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDTO {
    @JsonProperty("id")
    private int id; // tmbd id

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("original_title")
    private String title;

    @JsonProperty("poster_path")
    private String poster;

    @JsonProperty("release_date")
    private String releaseDate;

    public MovieDTO() {
        // Empty constructor for Jackson
    }

    public MovieDTO(String title, String poster, String releaseDate) {
        this.title = title;
        this.poster = poster;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "MovieDTO [id=" + id + ", overview=" + overview + ", title=" + title + ", poster=" + poster
                + ", releaseDate=" + releaseDate + "]";
    }
}
