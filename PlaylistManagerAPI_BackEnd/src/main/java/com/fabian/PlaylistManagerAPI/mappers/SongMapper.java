package com.fabian.PlaylistManagerAPI.mappers;

import com.fabian.PlaylistManagerAPI.model.api.requests.SongRequest;
import com.fabian.PlaylistManagerAPI.model.entities.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    Song toDomain(SongRequest songRequest);
}
