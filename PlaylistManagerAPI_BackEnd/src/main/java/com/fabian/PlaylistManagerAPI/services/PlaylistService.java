package com.fabian.PlaylistManagerAPI.services;

import com.fabian.PlaylistManagerAPI.model.api.requests.PlayListRequest;
import com.fabian.PlaylistManagerAPI.model.entities.Playlist;

public interface PlaylistService {
    Playlist createPlaylist(PlayListRequest a);



}
