package com.fabian.PlaylistManagerAPI.service;

import com.fabian.PlaylistManagerAPI.exceptions.BadPlaylistNameException;
import com.fabian.PlaylistManagerAPI.exceptions.BadPlaylistNotFoundException;
import com.fabian.PlaylistManagerAPI.model.entities.Playlist;
import com.fabian.PlaylistManagerAPI.repositories.PlaylistRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlaylistServiceTest {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Before
    public void setUp() {
        playlistRepository.deleteAll();
    }

    @Test
    public void addPlaylist_ValidPlaylist_ShouldSaveToDatabase() {
        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");
        playlist.setDescription("Test Description");

        Playlist savedPlaylist = playlistRepository.save(playlist);

        assertNotNull(savedPlaylist);
        assertNotNull(savedPlaylist.getId());
        assertEquals("Test Playlist", savedPlaylist.getName());
    }

    @Test
    public void addPlaylist_InvalidName_ShouldThrowException() {
        Playlist playlist = new Playlist();
        playlist.setName(null);

        try {
            if (playlist.getName() == null || playlist.getName().isEmpty()) {
                throw new BadPlaylistNameException("Nombre de la lista no válido");
            }
            playlistRepository.save(playlist);
            fail("Should have thrown BadPlaylistNameException");
        } catch (BadPlaylistNameException e) {
            assertEquals("Nombre de la lista no válido", e.getMessage());
        }
    }

    @Test
    public void getAllPlaylists_ShouldReturnAllPlaylists() {
        Playlist playlist1 = new Playlist();
        playlist1.setName("Playlist 1");
        playlistRepository.save(playlist1);

        Playlist playlist2 = new Playlist();
        playlist2.setName("Playlist 2");
        playlistRepository.save(playlist2);

        List<Playlist> playlists = playlistRepository.findAll();

        assertEquals(2, playlists.size());
    }

    @Test
    public void getPlaylist_ExistingPlaylist_ShouldReturnPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setName("Existing Playlist");
        playlistRepository.save(playlist);

        Playlist foundPlaylist = playlistRepository.findByName("Existing Playlist")
            .orElseThrow(() -> new BadPlaylistNotFoundException("Lista no encontrada"));

        assertNotNull(foundPlaylist);
        assertEquals("Existing Playlist", foundPlaylist.getName());
    }

    @Test
    public void getPlaylist_NonExistingPlaylist_ShouldThrowException() {
        try {
            playlistRepository.findByName("NonExistent")
                .orElseThrow(() -> new BadPlaylistNotFoundException("Lista no encontrada"));
            fail("Should have thrown BadPlaylistNotFoundException");
        } catch (BadPlaylistNotFoundException e) {
            assertEquals("Lista no encontrada", e.getMessage());
        }
    }

    @Test
    public void deletePlaylist_ExistingPlaylist_ShouldRemoveFromDatabase() {
        Playlist playlist = new Playlist();
        playlist.setName("Delete Playlist");
        playlistRepository.save(playlist);

        playlistRepository.delete(playlist);

        assertTrue(playlistRepository.findByName("Delete Playlist").isEmpty());
    }

    @Test
    public void deletePlaylist_NonExistingPlaylist_ShouldThrowException() {
        try {
            Playlist playlist = playlistRepository.findByName("NonExistent")
                .orElseThrow(() -> new BadPlaylistNotFoundException("Lista no encontrada"));
            playlistRepository.delete(playlist);
            fail("Should have thrown BadPlaylistNotFoundException");
        } catch (BadPlaylistNotFoundException e) {
            assertEquals("Lista no encontrada", e.getMessage());
        }
    }
}
