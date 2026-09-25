package com.immersia.immersiabackend.repository;

import com.immersia.immersiabackend.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite , Long> {
    List<Favorite> findByUserId(Long userId);
    Optional <Favorite> findByIdAndUserId(Long id , Long userId);
    boolean existsByUserIdAndMovieId(Long userId , Long movieId);


}
