package com.immersia.immersiabackend.service;

import com.immersia.immersiabackend.exception.FavoriteAlreadyExistsException;
import com.immersia.immersiabackend.model.Favorite;
import com.immersia.immersiabackend.model.User;
import com.immersia.immersiabackend.repository.FavoriteRepository;

import com.immersia.immersiabackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository ,
            UserRepository userRepository
    ){
        this.favoriteRepository = favoriteRepository;
        this.userRepository= userRepository;
    }
    public Favorite addFavorite(String email, Long moveiId){
      User user = userRepository.findByEmail(email)
              .orElseThrow(() -> new RuntimeException("User not found"));
      boolean alreadyExists =
              favoriteRepository.existsByUserIdAndMovieId(
                      user.getId(),
                      moveiId
              );
      if (alreadyExists){
          throw  new FavoriteAlreadyExistsException(
                  "Movie already in favorites"
          );
      }
      Favorite favorite = new Favorite(
              user.getId(),
              moveiId
      );
      return favoriteRepository.save(favorite);
    }
    public List<Favorite> getFavorites(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return favoriteRepository.findByUserId(user.getId());
    }
    public void deleteFavorite(String email , Long id){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Favorite favorite = favoriteRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        favoriteRepository.delete(favorite);
    }

}
