package com.fabian.PlaylistManagerAPI.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private String album;
    @Column(name = "release_year")
    private LocalDate year;
    private String genre;
}
