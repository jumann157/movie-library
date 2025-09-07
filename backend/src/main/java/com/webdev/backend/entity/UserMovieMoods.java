package com.webdev.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "User_Movie_Moods")
public class UserMovieMoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_movie_id")
    private UserMovies userMovies;

    @ManyToOne
    @JoinColumn(name = "mood_id")
    private Mood mood;

    @Column(
        name = "modified_on",
        nullable = true
    )
    private LocalDateTime modifiedOn;
    public UserMovieMoods() {

    }

    public UserMovieMoods(UserMovies userMovies, Mood mood) {
        this.userMovies = userMovies;
        this.mood = mood;
        this.modifiedOn = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserMovies getUserMovies() {
        return userMovies;
    }

    public void setUserMovies(UserMovies userMovies) {
        this.userMovies = userMovies;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
