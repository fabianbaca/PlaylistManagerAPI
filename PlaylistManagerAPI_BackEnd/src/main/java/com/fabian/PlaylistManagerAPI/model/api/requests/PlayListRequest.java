package com.fabian.PlaylistManagerAPI.model.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlayListRequest {

    @NotNull
    @JsonProperty("nombre")
    private String name;

    @NotNull
    @JsonProperty("descripcion")
    private String description;

    @NotNull
    @JsonProperty("canciones")
    private List<@Valid SongRequest> songRespons = new ArrayList<>();

}
