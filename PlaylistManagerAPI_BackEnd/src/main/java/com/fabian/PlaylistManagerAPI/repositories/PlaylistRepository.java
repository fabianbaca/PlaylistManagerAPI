package com.fabian.PlaylistManagerAPI.repositories;

import com.fabian.PlaylistManagerAPI.model.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
