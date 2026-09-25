package com.immersia.immersiabackend.test;

import com.immersia.immersiabackend.controller.WatchlistController;
import com.immersia.immersiabackend.dto.WatchlistResponse;
import com.immersia.immersiabackend.service.WatchlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


public class WatchlistControllerTest {
    private MockMvc mockMvc;
    @Mock
    private WatchlistService watchlistService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new WatchlistController(watchlistService))
                .build();
    }
    @Test
    void shouldGetWatchlist() throws Exception {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName())
                .thenReturn("test@gmail.com");

        Mockito.when(watchlistService.getWatchlist("test@gmail.com"))
                .thenReturn(List.of());
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/watchlist")
                                .principal(authentication)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(watchlistService)
                .getWatchlist("test@gmail.com");
    }
    @Test
    void shouldAddWatchlist() throws Exception {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName())
                .thenReturn("test@gmail.com");
        Mockito.when(watchlistService.addWatchlist("test@gmail.com", 1L))
                .thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/watchlist")
                                .param("movieId", "1")
                                .principal(authentication)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(watchlistService)
                .addWatchlist("test@gmail.com", 1L);
    }
    @Test
    void shouldDeleteWatchlist() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/watchlist/1")
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(watchlistService)
                .deletewatchlist(1L);
    }

}
