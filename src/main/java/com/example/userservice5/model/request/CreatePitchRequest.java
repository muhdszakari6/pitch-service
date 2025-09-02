package com.example.userservice5.model.request;

import com.example.userservice5.enums.PitchType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class CreatePitchRequest {
    @NotNull()
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String name;

    @NotNull()
    @NotBlank(message = "Location cannot be blank")
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String location;

    @NotNull()
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @Valid
    @NotNull()
    private List<SessionModel> sessions;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SessionModel> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionModel> sessions) {
        this.sessions = sessions;
    }
}

