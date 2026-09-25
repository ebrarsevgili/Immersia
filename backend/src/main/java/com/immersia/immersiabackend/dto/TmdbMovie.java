package com.immersia.immersiabackend.dto;


public class TmdbMovie {

    private Long id;
    private String title;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private double vote_average;

    public TmdbMovie(){

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
}
