package com.webdev.backend.services;

import com.webdev.backend.models.MovieResponse;
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

    public MovieResponse searchMovies(String query) {
        return webClient.get().uri("/search/movie?api_key={apiKey}&query={query}", apiKey, query)
                        .retrieve()
                        .bodyToMono(MovieResponse.class)
                        .block();
    }
}
