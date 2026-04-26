package com.example.userservice5.dto;

import com.example.userservice5.enums.PitchType;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class PitchDto {
    private Long id;
    private String name;
    private String location;
    private PitchType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SessionDto> sessions;
    private SessionConfigurationDto sessionConfiguration;

    private UserDto userDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PitchType getType() {
        return type;
    }

    public void setType(PitchType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SessionDto> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDto> sessions) {
        this.sessions = sessions;
    }

    public SessionConfigurationDto getSessionConfiguration() {
        return sessionConfiguration;
    }

    public void setSessionConfiguration(SessionConfigurationDto sessionConfiguration) {
        this.sessionConfiguration = sessionConfiguration;
    }

    public UserDto getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDto user) {
        this.userDetail = user;
    }

    public Long getSessionCount() {
        return sessions != null ? (long) sessions.size() : 0L;
    }
}
