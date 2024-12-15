package com.fabian.PlaylistManagerAPI.repositories;

import com.fabian.PlaylistManagerAPI.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}