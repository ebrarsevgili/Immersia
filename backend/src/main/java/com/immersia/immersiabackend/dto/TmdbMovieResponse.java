package com.immersia.immersiabackend.dto;


import java.util.List;


public class TmdbMovieResponse {
    private int page;
    private List<TmdbMovie> results;
    private int total_pages;
    private int total_results;

    public TmdbMovieResponse(){

    }
    public int getPage(){ return page;}
    public List<TmdbMovie> getResults(){ return results;}
    public int getTotal_pages(){ return total_pages;}
    public int getTotal_results(){ return total_results;}

}
