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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tmdbId == null) ? 0 : tmdbId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((overview == null) ? 0 : overview.hashCode());
        result = prime * result + ((posterPath == null) ? 0 : posterPath.hashCode());
        result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (tmdbId == null) {
            if (other.tmdbId != null)
                return false;
        } else if (!tmdbId.equals(other.tmdbId))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (overview == null) {
            if (other.overview != null)
                return false;
        } else if (!overview.equals(other.overview))
            return false;
        if (posterPath == null) {
            if (other.posterPath != null)
                return false;
        } else if (!posterPath.equals(other.posterPath))
            return false;
        if (releaseDate == null) {
            if (other.releaseDate != null)
                return false;
        } else if (!releaseDate.equals(other.releaseDate))
            return false;
        return true;
    }
    

}
