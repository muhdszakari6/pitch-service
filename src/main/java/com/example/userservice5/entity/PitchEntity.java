package com.example.userservice5.entity;

import com.example.userservice5.enums.PitchType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "pitch")
public class PitchEntity implements Serializable {

    private static final long serialVersionUID = -7986089247891906665L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private PitchType type;

    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Setter(value = AccessLevel.NONE)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "pitch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<SessionEntity> sessions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_configuration_id")
    private SessionConfigurationEntity sessionConfiguration;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    },fetch = FetchType.LAZY )
    @JoinColumn(name = "users_id")
    private UserEntity userDetail;

    @Getter
    @Setter
    private LocalDateTime deletedAt;

    public UserEntity getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserEntity userDetail) {
        this.userDetail = userDetail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Collection<SessionEntity> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<SessionEntity> sessions) {
        this.sessions = sessions;
        // Ensure bidirectional relationship
        if (sessions != null) {
            sessions.forEach(session -> session.setPitch(this));
        }
    }
    
    public void addSession(SessionEntity session) {
        if (this.sessions == null) {
            this.sessions = new ArrayList<>();
        }
        this.sessions.add(session);
        session.setPitch(this);
    }
    
    public void removeSession(SessionEntity session) {
        if (this.sessions != null) {
            this.sessions.remove(session);
            session.setPitch(null);
        }
    }

    public SessionConfigurationEntity getSessionConfiguration() {
        return sessionConfiguration;
    }

    public void setSessionConfiguration(SessionConfigurationEntity sessionConfiguration) {
        this.sessionConfiguration = sessionConfiguration;
    }
}
