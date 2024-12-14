package com.fabian.PlaylistManagerAPI.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists = new ArrayList<>();;
}
