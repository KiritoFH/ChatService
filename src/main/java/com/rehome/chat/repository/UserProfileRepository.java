package com.rehome.chat.repository;

import com.rehome.chat.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);

    Boolean existsByUsername(String username);
}
