package com.example.userservice5.model.response;

import com.example.userservice5.enums.PitchType;
import com.example.userservice5.model.request.SessionModel;

import java.util.List;

public class UpdatePitchResponse {
    private long id;
    private String name;
    private String location;
    private PitchType type;
    private List<SessionModel> sessions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<SessionModel> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionModel> sessions) {
        this.sessions = sessions;
    }
}