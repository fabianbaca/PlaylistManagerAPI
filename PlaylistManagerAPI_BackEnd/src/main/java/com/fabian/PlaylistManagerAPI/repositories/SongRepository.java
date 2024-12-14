package com.fabian.PlaylistManagerAPI.repositories;

import com.fabian.PlaylistManagerAPI.model.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository  extends JpaRepository<Song, Long> {
}
