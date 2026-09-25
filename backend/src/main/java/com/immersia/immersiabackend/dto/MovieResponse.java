package com.immersia.immersiabackend.dto;


public class MovieResponse {
    private Long id;
    private String title;
    private int year;
    private double rating;
    private String description;
    private String image;
    private String backdropImage;

    public MovieResponse(){

    }
    public MovieResponse(
            Long id,
            String title,
            int year,
            double rating,
            String description,
            String image,
            String backdropImage
    ){
        this.id=id;
        this.title=title;
        this.year=year;
        this.rating=rating;
        this.description=description;
        this.image=image;
        this.backdropImage=backdropImage;
    }
    public Long getId(){ return  id ; }
    public String getTitle(){ return title ;}
    public int getYear() {
        return year;
    }
    public double getRating() {
        return rating;
    }
    public String getDescription() {
        return description;
    }
    public String getImage() {
        return image;
    }
    public String getBackdropImage() {
        return backdropImage;
    }
}
