package com.immersia.immersiabackend.model;

import  jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String title;
    private int year;
    private double rating;

    public Movie(){

    }
    public Movie (String title , int year ,double rating){
        this.title= title;
        this.year= year;
        this.rating = rating;
    }

    public String getTitle(){
        return title;
    }
    public int getYear(){
        return year;
    }
    public double getRating(){
        return  rating;
    }



}
