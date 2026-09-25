package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.exception.FavoriteAlreadyExistsException;
import com.immersia.immersiabackend.model.Favorite;
import com.immersia.immersiabackend.model.User;
import com.immersia.immersiabackend.repository.FavoriteRepository;
import com.immersia.immersiabackend.repository.UserRepository;
import com.immersia.immersiabackend.service.FavoriteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTest {
    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private FavoriteService favoriteService;
    @Test
    void shouldThrowExceptionWhenFavoriteAlreadyExists(){
        User user = new User(
                "testuser",
                "test@gmail.com",
                "123456"
        );
        user.setId(4L);
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        Mockito.when(favoriteRepository.existsByUserIdAndMovieId(4L,550L))
                .thenReturn(true);
        Assertions.assertThrows(
                FavoriteAlreadyExistsException.class,
                ()-> favoriteService.addFavorite("test@gmail.com" , 550L)
        );
    }
    @Test
    void shouldSaveFavoriteWhenFavoriteDoesNotExist(){
        User user = new User (
                "testuser",
                "test@gmail.com",
                "123456"
        );
        user.setId(4L);
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        Mockito.when(favoriteRepository.existsByUserIdAndMovieId(4L , 550L))
                .thenReturn(false);
        favoriteService.addFavorite("test@gmail.com" , 550L );
        Mockito.verify(favoriteRepository).save(ArgumentMatchers.any());
    }
    @Test
    void shouldReturnFavoritesForUser(){
        User user = new User(
                "testuser",
                "test@gmail.com",
                "123456"
        );
        user.setId(4L);
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        Favorite favorite1 = new Favorite(4L, 550L);
        Favorite favorite2 = new Favorite(4L, 551L);
        List<Favorite> favorites = List.of(favorite1 , favorite2);
        Mockito.when(favoriteRepository.findByUserId(4L))
                .thenReturn(favorites);
        List<Favorite> result =
                favoriteService.getFavorites("test@gmail.com");
        Assertions.assertEquals(2,result.size());
    }
    @Test
    void shouldDeleteFavoriteForUser(){
        User user = new User(
                "testuser",
                "test@gmail.com",
                "123456"
        );
        user.setId(4L);
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        Favorite favorite = new Favorite(4L , 550L);
        favorite.setId(1L);
        Mockito.when(favoriteRepository.findByIdAndUserId(1L ,4L))
                .thenReturn(Optional.of(favorite));
        favoriteService.deleteFavorite("test@gmail.com" , 1L);
        Mockito.verify(favoriteRepository).delete(favorite);
    }
}
