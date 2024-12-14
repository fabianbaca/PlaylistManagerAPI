package com.fabian.PlaylistManagerAPI.repositories;

import com.fabian.PlaylistManagerAPI.model.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Query("SELECT p FROM Playlist p WHERE p.name = :name")
    Optional<Playlist> findByName(@Param("name") String name);

}
