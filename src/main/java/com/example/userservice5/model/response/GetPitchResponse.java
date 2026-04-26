package com.example.userservice5.model.response;

import com.example.userservice5.enums.PitchType;
import com.example.userservice5.model.request.SessionModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetPitchResponse {
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String location;

    @Getter
    @Setter
    private PitchType type;

    @Getter
    @Setter
    private Long sessionCount;

    @Getter
    @Setter
    private List<SessionModel> sessions;

}
