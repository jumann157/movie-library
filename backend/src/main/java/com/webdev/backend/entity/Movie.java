package com.webdev.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        name = "tmdb_id",
        nullable = true,
        unique = true
    )
    private Integer tmdbId;

    @Column(
        name = "title",
        nullable = false,
        unique = false
    )
    private String title;

    @Column(
        name = "overview",
        nullable = true,
        unique = false,
        columnDefinition = "TEXT"
    )
    private String overview;

    @Column(
        name = "poster_path",
        nullable = true,
        unique = false
    )
    private String posterPath;

    @Column(
        name = "release_date",
        nullable = true,
        unique = false
    )
    private LocalDate releaseDate; 

    public Movie() {

    }

    public Movie(Integer tmdbId, String title, String overview, String posterPath, LocalDate releaseDate) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReleaseYear() {
        return releaseDate != null ? releaseDate.getYear() : 0; // if releaseDate is not null (condition), return release Year OR 0
    }
    

}
