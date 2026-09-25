package com.immersia.immersiabackend.dto;

public class WatchlistResponse {
    private Long id;
    private Long movieId;

    public WatchlistResponse(Long id  , Long movieId){
        this.id = id;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }
}
