package com.example.userservice5.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "session")
public class SessionEntity implements Serializable {

    private static final long serialVersionUID = 7921316627882589533L;
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private LocalTime startTime;

    private LocalTime endTime;

    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Setter(value = AccessLevel.NONE)
    private LocalDateTime updatedAt;

    @Column()
    private Boolean active;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "pitch_id")
    private PitchEntity pitch;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public PitchEntity getPitch() {
        return pitch;
    }

    public void setPitch(PitchEntity pitch) {
        // Remove from old pitch if exists
        if (this.pitch != null && this.pitch.getSessions() != null) {
            this.pitch.getSessions().remove(this);
        }
        
        this.pitch = pitch;
        
        // Add to new pitch if exists
        if (pitch != null && pitch.getSessions() != null && !pitch.getSessions().contains(this)) {
            pitch.getSessions().add(this);
        }
    }
}
