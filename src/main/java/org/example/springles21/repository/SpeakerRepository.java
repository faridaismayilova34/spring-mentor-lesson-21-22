package org.example.springles21.repository;

import org.example.springles21.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    boolean existsByEmail(String email);
}