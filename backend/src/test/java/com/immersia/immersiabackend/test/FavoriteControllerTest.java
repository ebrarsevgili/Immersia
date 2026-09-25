package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.controller.FavoriteController;
import com.immersia.immersiabackend.model.Favorite;
import com.immersia.immersiabackend.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class FavoriteControllerTest {
    private MockMvc mockMvc ;
    @Mock
    private FavoriteService favoriteService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FavoriteController(favoriteService))
                .build();
    }
    @Test
    void shouldAddFavorites() throws Exception{
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
        when(authentication.getName())
                .thenReturn("test@gmail.com");
        Favorite favorite = new Favorite(4L ,550L);
        when(favoriteService.addFavorite("test@gmail.com" , 550L))
                .thenReturn(favorite);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/favorites")
                        .param("movieId" , "550")
                        .principal(authentication)
                );
        verify(favoriteService)
                .addFavorite("test@gmail.com" , 550L);
    }
    @Test
    void shouldGetFavorites() throws Exception{
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName())
                .thenReturn("test@gmail.com");
        List<Favorite> favorites = List.of(
                new Favorite(4L, 550L),
                new Favorite(4L, 551L)
        );
        when(favoriteService.getFavorites("test@gmail.com"))
                .thenReturn(favorites);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/favorites")
                        .principal(authentication)
        );
        verify(favoriteService).getFavorites("test@gmail.com");
    }
    @Test
    void shouldDeleteFavorite() throws Exception{
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName())
                .thenReturn("test@gmail.com");
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/favorites/1")
                        .principal(authentication)
        );
        verify(favoriteService).deleteFavorite("test@gmail.com" ,1L);
    }
}
