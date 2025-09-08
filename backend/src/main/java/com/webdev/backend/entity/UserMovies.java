package com.webdev.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "User_Movies")
public class UserMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Many UserMovies have One User
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    private User user;

    @ManyToOne // Many UserMovies have One Movie
    @JoinColumn(
        name = "movie_id",
        nullable = false
    )
    private Movie movie;

    @Column(
        name = "watched",
        nullable = false
    )
    private boolean watched;

    @Column(
        name = "rating",
        nullable = true,
        unique = false
    )
    private Integer rating; 

    @Column(
        name = "comments",
        nullable = true,
        unique = false,
        columnDefinition = "TEXT"
    )
    private String comments;

    @Column(
        name = "added_on",
        nullable = false,
        unique = false
    )
    private LocalDateTime addedOn;

    public UserMovies() {

    }

    public UserMovies(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.addedOn = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }
}
