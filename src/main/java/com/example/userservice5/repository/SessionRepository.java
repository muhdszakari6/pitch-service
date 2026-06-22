package com.example.userservice5.repository;
import com.example.userservice5.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    List<SessionEntity> findByPitchIdAndActiveTrue(Long pitchId);
}
