package com.example.userservice5.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    @NotNull()
    @NotBlank(message = "New password cannot be blank")
    private String newPassword;
    @NotNull()
    @NotBlank(message = "Confirm new password cannot be blank")
    private String confirmNewPassword;
    @NotNull()
    @NotBlank(message = "Old password cannot be blank")
    private String oldPassword;
}
