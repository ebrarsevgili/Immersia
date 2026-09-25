package com.immersia.immersiabackend.dto;

import java.util.List;

public class MovieDetailResponse {
    private Long id;
    private String title;
    private String description;
    private int year;
    private double rating;
    private String image;
    private String backdropImage;
    private int duration;
    private List<String> genres;
    private String director;
    private List<String> cast;

    public MovieDetailResponse() {
    }

    public MovieDetailResponse(
            Long id,
            String title,
            String description,
            int year,
            double rating,
            String image,
            String backdropImage,
            int duration,
            List<String> genres,
            String director,
            List<String> cast
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.rating = rating;
        this.image = image;
        this.backdropImage = backdropImage;
        this.duration = duration;
        this.genres = genres;
        this.director =director;
        this.cast=cast;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getCast() {
        return cast;
    }
}
