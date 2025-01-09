package com.example.userservice5.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "password_rest_tokens")
public class PasswordResetTokenEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2975980712162349585L;
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String token;

    @OneToOne()
    @JoinColumn(name = "users_id")
    private UserEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
