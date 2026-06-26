package com.example.userservice5.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    @NotNull()
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;
    @NotNull()
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;
}
