package com.example.userservice5.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

public class SessionModel {

    @Getter
    @Setter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private LocalTime startTime;

    @Setter
    @Getter
    private LocalTime endTime;

    @Setter
    @Getter
    private Boolean active;

}
