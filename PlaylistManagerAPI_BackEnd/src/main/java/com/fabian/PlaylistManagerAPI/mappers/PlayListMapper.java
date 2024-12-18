package com.fabian.PlaylistManagerAPI.mappers;

import com.fabian.PlaylistManagerAPI.model.api.requests.PlayListRequest;
import com.fabian.PlaylistManagerAPI.model.entities.Playlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayListMapper {

    Playlist toDomain(PlayListRequest playListRequest);

}
