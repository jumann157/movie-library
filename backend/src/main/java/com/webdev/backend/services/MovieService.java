package com.webdev.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieService {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public MovieService() {
        this.webClient = WebClient.builder()
                        .baseUrl("https://api.themoviedb.org/3/")
                        .build();
    }

    
}
