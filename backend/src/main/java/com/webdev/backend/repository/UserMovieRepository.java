package com.webdev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdev.backend.entity.UserMovies;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovies, Long>{

}
