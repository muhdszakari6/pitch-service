package com.example.userservice5.repository;

import com.example.userservice5.entity.SessionConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionConfigurationRepository extends JpaRepository<SessionConfigurationEntity, Long> {

}
