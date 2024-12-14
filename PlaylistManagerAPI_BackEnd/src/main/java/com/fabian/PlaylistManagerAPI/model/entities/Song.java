package com.fabian.PlaylistManagerAPI.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private String album;
    private String releaseYear;
    private String genre;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists = new ArrayList<>();
}
