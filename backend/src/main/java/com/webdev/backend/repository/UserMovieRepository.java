package com.webdev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdev.backend.entity.User;
import com.webdev.backend.entity.UserMovies;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovies, Long>{
    List<UserMovies> findByUser(User user);
}
