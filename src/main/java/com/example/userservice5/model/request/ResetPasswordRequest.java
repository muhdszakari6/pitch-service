package com.example.userservice5.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequest {
    @NotNull()
    @NotBlank()
    private String resetPasswordToken;

    @NotNull()
    @NotBlank(message = "Password can't be blank")
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String password;

    @NotNull()
    @NotBlank(message = "Confirm Password can't be blank")
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String confirmPassword;

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
