package com.fabian.PlaylistManagerAPI.controllers;

import com.fabian.PlaylistManagerAPI.model.api.requests.SongRequest;
import com.fabian.PlaylistManagerAPI.model.entities.Playlist;
import com.fabian.PlaylistManagerAPI.repositories.PlaylistRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaylistControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PlaylistRepository playlistRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        playlistRepository.deleteAll();
    }


    @Test
    public void addPlaylist_ShouldReturnCreatedPlaylist() throws Exception {
        String playlistJson = "{\n" +
                "  \"nombre\": \"Lista 1\",\n" +
                "  \"descripcion\": \"Lista de canciones de Spotify\",\n" +
                "  \"canciones\": [\n" +
                "    {\n" +
                "      \"titulo\": \"Shape of You\",\n" +
                "      \"artista\": \"Ed Sheeran\",\n" +
                "      \"album\": \"Divide\",\n" +
                "      \"anno\": \"2017\",\n" +
                "      \"genero\": \"Pop\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"titulo\": \"Blinding Lights\",\n" +
                "      \"artista\": \"The Weeknd\",\n" +
                "      \"album\": \"After Hours\",\n" +
                "      \"anno\": \"2020\",\n" +
                "      \"genero\": \"Pop\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"titulo\": \"Levitating\",\n" +
                "      \"artista\": \"Dua Lipa\",\n" +
                "      \"album\": \"Future Nostalgia\",\n" +
                "      \"anno\": \"2020\",\n" +
                "      \"genero\": \"Pop\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(post("/lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playlistJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Lista 1"));

        assertEquals(1, playlistRepository.findAll().size());
    }

    @Test
    public void getAllPlaylists_ShouldReturnListOfPlaylists() throws Exception {
        Playlist playlist1 = new Playlist();
        playlist1.setName("Playlist 1");
        playlistRepository.save(playlist1);

        Playlist playlist2 = new Playlist();
        playlist2.setName("Playlist 2");
        playlistRepository.save(playlist2);

        mockMvc.perform(get("/lists")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Playlist 1"))
            .andExpect(jsonPath("$[1].name").value("Playlist 2"));
    }

    @Test
    public void getPlaylist_ShouldReturnPlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlistRepository.save(playlist);

        mockMvc.perform(get("/lists/Test Playlist")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Test Playlist"));
    }

    @Test
    public void deletePlaylist_ShouldReturnNoContent() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlistRepository.save(playlist);

        mockMvc.perform(delete("/lists/Test Playlist")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        assertTrue(playlistRepository.findByName("Test Playlist").isEmpty());
    }
}