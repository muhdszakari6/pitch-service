package com.example.userservice5.repository;
import com.example.userservice5.entity.PitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PitchRepository extends JpaRepository<PitchEntity, Long> {
    PitchEntity findByName(String name);
    Optional<PitchEntity> findById(Long id);
}
