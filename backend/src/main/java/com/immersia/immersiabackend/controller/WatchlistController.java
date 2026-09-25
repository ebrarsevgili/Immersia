package com.immersia.immersiabackend.controller;


import com.immersia.immersiabackend.dto.WatchlistResponse;
import com.immersia.immersiabackend.model.Watchlist;
import com.immersia.immersiabackend.service.WatchlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    private final WatchlistService watchlistService;
    public WatchlistController(WatchlistService watchlistService){
        this.watchlistService = watchlistService;
    }
    @GetMapping
    public ResponseEntity<List<WatchlistResponse>> getWatchlist(
            Authentication authentication
    ){
        String email = authentication.getName();
        return ResponseEntity.ok(
                watchlistService.getWatchlist(email)
        );
    }
    @PostMapping
    public ResponseEntity<WatchlistResponse> addWatchlist(
            @RequestParam Long movieId,
            Authentication authentication
    ){
        String email = authentication.getName();
        return ResponseEntity.ok(
                watchlistService.addWatchlist(email, movieId)
        );
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteWatchlist(
            @PathVariable Long id
    ){
        watchlistService.deletewatchlist(id);
        return ResponseEntity.noContent().build();
    }
}
