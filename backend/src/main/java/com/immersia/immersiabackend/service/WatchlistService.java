package com.immersia.immersiabackend.service;

import com.immersia.immersiabackend.dto.WatchlistResponse;
import com.immersia.immersiabackend.model.User;
import com.immersia.immersiabackend.model.Watchlist;
import com.immersia.immersiabackend.repository.UserRepository;
import com.immersia.immersiabackend.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    public WatchlistService(
            WatchlistRepository watchlistRepository,
            UserRepository userRepository
    ){
        this.watchlistRepository=watchlistRepository;
        this.userRepository = userRepository;
    }
    public WatchlistResponse addWatchlist(String email , Long movieId){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        boolean exists =
                watchlistRepository
                .existsByUserIdAndMovieId(user.getId() ,movieId);
        if (exists){
            throw  new RuntimeException("Movie already exsists in watchlist");
        }
        Watchlist watchlist = new Watchlist(
                user.getId(),
                movieId
        );
        Watchlist savedWatchlist = watchlistRepository.save(watchlist);
        return new WatchlistResponse(
                savedWatchlist.getId(),
                savedWatchlist.getMovieId()

        );
    }

    public List<WatchlistResponse> getWatchlist(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

        return watchlistRepository.findByUserId(user.getId())
                .stream()
                .map(watchlist -> new WatchlistResponse(
                        watchlist.getId(),
                        watchlist.getMovieId()
                ))
                .toList();
    }
    public void deletewatchlist(Long id){
        watchlistRepository.deleteById(id);
    }
}
