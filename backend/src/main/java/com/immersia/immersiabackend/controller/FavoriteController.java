package com.immersia.immersiabackend.controller;

import com.immersia.immersiabackend.model.Favorite;
import com.immersia.immersiabackend.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")

public class FavoriteController {
    private final FavoriteService favoriteService;
    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }
    @PostMapping
    public Favorite addFavorite(
            @RequestParam Long movieId,
            Authentication authentication
    ){
        String email = authentication.getName();
        return favoriteService.addFavorite(email , movieId);
    }
    @GetMapping
    public List<Favorite> getFavorites(
            Authentication authentication
    ){
        String email = authentication.getName();
        return favoriteService.getFavorites(email);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorites(
            @PathVariable Long id,
            Authentication authentication
    ){
        String email = authentication.getName();
        favoriteService.deleteFavorite(email , id);
        return ResponseEntity.noContent().build();
    }

}
