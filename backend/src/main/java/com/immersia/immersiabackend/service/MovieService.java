package com.immersia.immersiabackend.service;

import com.immersia.immersiabackend.dto.*;
import com.immersia.immersiabackend.model.Movie;
import com.immersia.immersiabackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final RestClient restClient;
    private  final String tmdbApikey;

    public MovieService(
            MovieRepository movieRepository,
            RestClient.Builder restClientBuilder,
            @Value("${tmdb.api.key}") String tmdbApikey
    ){
        this.movieRepository = movieRepository;
        this.restClient = restClientBuilder.build();
        this.tmdbApikey = tmdbApikey;
    }
    public List<Movie> getAllMovie(){
        return movieRepository.findAll();
    }
    public TmdbMovieResponse getPopularMovieFromTmdb(){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/popular")
                        .queryParam("api_key" , tmdbApikey)
                        .queryParam("language" , "tr-TR")
                        .build())
                .retrieve()
                .body(TmdbMovieResponse.class);
    }
    public List<MovieResponse> getPopularMovies(){
        TmdbMovieResponse tmdbResponse = getPopularMovieFromTmdb();
        return tmdbResponse.getResults()
                .stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle(),
                        getYear(movie.getRelease_date()),
                        movie.getVote_average(),
                        movie.getOverview(),
                        buildImageUrl(movie.getPoster_path()),
                        buildImageUrl(movie.getBackdrop_path())

                ))
                .collect(Collectors.toList());
    }

    public List<MovieResponse> getMovies(int page){
        TmdbMovieResponse tmdbMovieResponse = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/discover/movie")
                        .queryParam("api_key", tmdbApikey)
                        .queryParam("language", "tr-TR")
                        .queryParam("page", page)
                        .queryParam("sort_by", "popularity.desc")
                        .build())
                .retrieve()
                .body(TmdbMovieResponse.class);
        return tmdbMovieResponse.getResults()
                .stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle(),
                        getYear(movie.getRelease_date()),
                        movie.getVote_average(),
                        movie.getOverview(),
                        buildImageUrl(movie.getPoster_path()),
                        buildImageUrl(movie.getBackdrop_path())
                ))
                .collect(Collectors.toList());
        }
    public  List<MovieResponse> getSmilarMovies(Long id ){
        TmdbMovieResponse tmdbResponse = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("3/movie/{id}/similar")
                        .queryParam("api_key" , tmdbApikey)
                        .queryParam("language" , "tr-TR")
                        .build(id))
                .retrieve()
                .body(TmdbMovieResponse.class);
        return tmdbResponse.getResults()
                .stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle(),
                        getYear(movie.getRelease_date()),
                        movie.getVote_average(),
                        movie.getOverview(),
                        buildImageUrl(movie.getPoster_path()),
                        buildImageUrl(movie.getBackdrop_path())
                ))
                .limit(5)
                .collect(Collectors.toList());
    }

    public int getYear(String releaseDate){
        if (releaseDate == null || releaseDate.isEmpty()){
            return 0;
        }
        return Integer.parseInt(releaseDate.substring(0,4));
    }
    public String buildImageUrl(String image_path){
        if (image_path == null || image_path.isEmpty()){
            return null;
        }
        return "https://image.tmdb.org/t/p/original" + image_path;
    }
    public List<MovieResponse> searchMovies(String query){
        TmdbMovieResponse tmdbResponse = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("3/search/movie")
                        .queryParam("api_key" , tmdbApikey)
                        .queryParam("language" , "tr-TR")
                        .queryParam("query" , query)
                        .build())
                .retrieve()
                .body(TmdbMovieResponse.class);
        return tmdbResponse.getResults()
                .stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle(),
                        getYear(movie.getRelease_date()),
                        movie.getVote_average(),
                        movie.getOverview(),
                        buildImageUrl(movie.getPoster_path()),
                        buildImageUrl(movie.getBackdrop_path())
                ))
                .collect(Collectors.toList());
    }
    public MovieDetailResponse getMovieDetail(Long id){
        TmdbMovieDetail movie = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/3/movie/{id}")
                        .queryParam("api_key" , tmdbApikey)
                        .queryParam("language" , "tr-TR")
                        .build(id))
                .retrieve()
                .body(TmdbMovieDetail.class);
        TmdbCreditsResponse credits = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("3/movie/{id}/credits")
                        .queryParam("api_key" , tmdbApikey)
                        .queryParam("language" ,"tr-TR")
                        .build(id))
                .retrieve()
                .body(TmdbCreditsResponse.class);
        String director = credits.getCrew()
                .stream()
                .filter(person -> person.getJob().equals("Director"))
                .map(TmdbCreditsResponse.Crew::getName)
                .findFirst()
                .orElse("Bilinmiyor");

        List<String> cast = credits.getCast()
                .stream()
                .limit(5)
                .map(TmdbCreditsResponse.Cast::getName)
                .collect(Collectors.toList());

        List <String> genres = movie.getGenres()
                .stream()
                .map(TmdbMovieDetail.Genre::getName)
                .collect(Collectors.toList());

        return new MovieDetailResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getOverview(),
                getYear(movie.getRelease_date()),
                movie.getVote_average(),
                buildImageUrl(movie.getPoster_path()),
                buildImageUrl(movie.getBackdrop_path()),
                movie.getRuntime(),
                genres,
                director,
                cast
        );
    }
}