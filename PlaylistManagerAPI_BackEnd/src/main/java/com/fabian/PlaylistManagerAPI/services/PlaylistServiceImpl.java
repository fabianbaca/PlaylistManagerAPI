package com.fabian.PlaylistManagerAPI.services;

import com.fabian.PlaylistManagerAPI.exceptions.BadPlaylistNameException;
import com.fabian.PlaylistManagerAPI.exceptions.BadPlaylistNotFoundException;
import com.fabian.PlaylistManagerAPI.mappers.PlayListMapper;
import com.fabian.PlaylistManagerAPI.mappers.SongMapper;
import com.fabian.PlaylistManagerAPI.model.api.requests.PlayListRequest;
import com.fabian.PlaylistManagerAPI.model.entities.Playlist;
import com.fabian.PlaylistManagerAPI.model.entities.Song;
import com.fabian.PlaylistManagerAPI.repositories.PlaylistRepository;
import com.fabian.PlaylistManagerAPI.repositories.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlayListMapper playListMapper;
    @Autowired
    private SongMapper mapper;

    @Override
    public Playlist createPlaylist(PlayListRequest playListRequest) {

        if (playListRequest.getName() == null || playListRequest.getName().isEmpty()) {
            throw new BadPlaylistNameException("Nombre de la lista no válido");
        }


        Optional<Playlist>  playlistOptional =playlistRepository.findByName(playListRequest.getName());
        if (playlistOptional.isPresent()) {
            throw new BadPlaylistNameException("Ya existe una lista con ese nombre");
        }


        List<Song> songList = playListRequest.getSongRequests().stream().map(mapper::toDomain).toList();

        List<String> titles = songList.stream()
                .map(Song::getTitle)
                .toList();

        List<Song> existingSongs = songRepository.findByTitleIn(titles);

        List<Song> songsToSave = songList.stream()
                .filter(song -> existingSongs.stream()
                        .noneMatch(existingSong -> existingSong.getTitle().equals(song.getTitle())))
                .toList();

        List<Song> savedSongs = songRepository.saveAll(songsToSave);

        List<Song> allSongs = new ArrayList<>();
        allSongs.addAll(existingSongs);
        allSongs.addAll(savedSongs);

        Playlist playlist = playListMapper.toDomain(playListRequest);
        playlist.setSongs(allSongs);

        return playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist getPlaylistByName(String listName) {
        Optional<Playlist> playlist = playlistRepository.findByName(listName);
        if (playlist.isPresent()) {
            return playlist.get();
        } else {
            throw new BadPlaylistNotFoundException("Lista no encontrada");
        }
    }

    @Override
    public void deletePlaylist(String listName) {
        Optional<Playlist> playlist = playlistRepository.findByName(listName);
        if (playlist.isPresent()) {
            playlistRepository.delete(playlist.get());
        } else {
            throw new BadPlaylistNotFoundException("Lista no encontrada");
        }
    }
}
