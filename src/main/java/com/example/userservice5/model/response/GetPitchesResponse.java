package com.example.userservice5.model.response;
import com.example.userservice5.enums.PitchType;
import lombok.Getter;
import lombok.Setter;

public class GetPitchesResponse {
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
}

