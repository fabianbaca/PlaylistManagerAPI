package com.fabian.PlaylistManagerAPI.services;

import com.fabian.PlaylistManagerAPI.model.api.requests.PlayListRequest;
import com.fabian.PlaylistManagerAPI.model.entities.Playlist;

import java.util.List;

public interface PlaylistService {
    Playlist createPlaylist(PlayListRequest a);

    List<Playlist> getAllPlaylists();

    Playlist getPlaylistByName(String listName);

    void deletePlaylist(String listName);
}
