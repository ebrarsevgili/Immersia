package com.immersia.immersiabackend.dto;

import java.util.List;

public class TmdbMovieDetail {
    private  Long id;
    private String title;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private double vote_average;
    private int runtime;
    private List<Genre> genres;

    public TmdbMovieDetail(){

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<Genre> getGenres() {
        return genres;
    }
    public static class Genre{
        private Long id;
        private String name;
        public Genre(){

        }
        public Long getId(){
            return id;
        }
        public String getName(){
            return  name;
        }

    }
}
