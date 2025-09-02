package com.example.userservice5.dto;

import com.example.userservice5.enums.CreationMode;

public class SessionConfigurationDto {
    private Long id;

    private int numberOfSessions;

    private CreationMode creationMode;

    private PitchDto pitch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfSessions() {
        return numberOfSessions;
    }

    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

    public CreationMode getCreationMode() {
        return creationMode;
    }

    public void setCreationMode(CreationMode creationMode) {
        this.creationMode = creationMode;
    }

    public PitchDto getPitch() {
        return pitch;
    }

    public void setPitch(PitchDto pitch) {
        this.pitch = pitch;
    }
}
