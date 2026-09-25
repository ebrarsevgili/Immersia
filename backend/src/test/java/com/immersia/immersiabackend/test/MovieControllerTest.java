package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.controller.MovieController;
import com.immersia.immersiabackend.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;



public class MovieControllerTest {
    private MockMvc mockMvc;
    @Mock
    private MovieService movieService;
    @BeforeEach
    void  setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new MovieController(movieService))
                .build();
    }
    @Test
    void shouldGetPopularMovies() throws Exception {

        Mockito.when(movieService.getPopularMovies())
                .thenReturn(List.of());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/movies")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(movieService).getPopularMovies();
    }
    @Test
    void shouldGetMovieDetail() throws Exception {

        Mockito.when(movieService.getMovieDetail(1L))
                .thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/movies/1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(movieService).getMovieDetail(1L);
    }
    @Test
    void shouldGetSimilarMovies() throws Exception {
        Mockito.when(movieService.getSmilarMovies(1L))
                .thenReturn(List.of());
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/movies/1/similar")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(movieService).getSmilarMovies(1L);
    }
    @Test
    void shouldSearchMovies() throws Exception {
        Mockito.when(movieService.searchMovies("inception"))
                .thenReturn(List.of());
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/movies/search")
                                .param("query", "inception")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(movieService).searchMovies("inception");
    }
}
