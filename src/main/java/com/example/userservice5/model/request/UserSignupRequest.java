package com.example.userservice5.model.request;

import jakarta.validation.constraints.*;

public class UserSignupRequest {
    @NotNull()
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    @NotBlank(message = "firstName can't be blank")
    private String firstName;
    @NotNull()
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    @NotBlank(message = "lastName can't be blank")
    private String lastName;
    @NotNull()
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email is invalid")
    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String email;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
