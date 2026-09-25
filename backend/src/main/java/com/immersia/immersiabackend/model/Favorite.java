package com.immersia.immersiabackend.model;

import jakarta.persistence.*;

@Entity
@Table(name= "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long movieId;

    public Favorite(){

    }
    public Favorite(Long userId , Long movieId){
        this.userId = userId;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMovieId() {
        return movieId;
    }
    public  void setId(Long id ){
        this.id= id;
    }
}
