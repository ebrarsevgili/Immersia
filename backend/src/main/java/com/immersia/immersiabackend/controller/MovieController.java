package com.immersia.immersiabackend.controller;


import com.immersia.immersiabackend.dto.MovieDetailResponse;
import com.immersia.immersiabackend.dto.MovieResponse;
import com.immersia.immersiabackend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173" , "http://localhost:5174"})
@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService  movieService){
        this.movieService = movieService;
    }
    @GetMapping("/api/movies")
    public List<MovieResponse> getMovies(
        @RequestParam(defaultValue = "1") int page
        ) {
            return movieService.getMovies(page);
        }
    @GetMapping("/api/movies/{id}")
    public MovieDetailResponse getMovieDeatil(@PathVariable Long id){
        return movieService.getMovieDetail(id);
    }
    @GetMapping("/api/movies/{id}/similar")
    public List<MovieResponse>getSimilarMovies(@PathVariable Long id){
        return movieService.getSmilarMovies(id);
    }
    @GetMapping("/api/movies/search")
    public List<MovieResponse> searchMovies(@RequestParam String query){
        return movieService.searchMovies(query);
    }


}
