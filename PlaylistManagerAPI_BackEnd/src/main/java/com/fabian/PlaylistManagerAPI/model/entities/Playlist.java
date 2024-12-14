package com.fabian.PlaylistManagerAPI.model.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;

}
