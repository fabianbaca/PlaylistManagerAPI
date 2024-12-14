package com.fabian.PlaylistManagerAPI.model.api.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class SongRequest {

    @NotNull
    @JsonProperty("titulo")
    private String title;

    @NotNull
    @JsonProperty("artista")
    private String artist;

    @NotNull
    @JsonProperty("album")
    private String album;

    @NotNull
    @JsonProperty("anno")
    private String releaseYear;

    @NotNull
    @JsonProperty("genero")
    private String genre;
}
