package com.fabian.PlaylistManagerAPI.controllers;


import com.fabian.PlaylistManagerAPI.model.api.requests.PlayListRequest;
import com.fabian.PlaylistManagerAPI.model.entities.Playlist;
import com.fabian.PlaylistManagerAPI.services.PlaylistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists")
@AllArgsConstructor
public class PlayListController {

    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<Playlist> savePlayList(@RequestBody @Valid  PlayListRequest playListRequest) {
        return new ResponseEntity<>(playlistService.createPlaylist(playListRequest), HttpStatus.CREATED);
    }

}
