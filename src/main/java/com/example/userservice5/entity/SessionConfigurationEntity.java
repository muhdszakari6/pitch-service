package com.example.userservice5.entity;

import com.example.userservice5.enums.CreationMode;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "session_configuration")
public class SessionConfigurationEntity implements Serializable {
    private static final long serialVersionUID = 7277911590252419147L;
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private int numberOfSessions;

    @Column()
    private CreationMode creationMode;

    @OneToOne(mappedBy = "sessionConfiguration", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private PitchEntity pitch;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getNumberOfSessions() {
        return numberOfSessions;
    }

    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

    public CreationMode getCreationMode() {
        return creationMode;
    }

    public void setCreationMode(CreationMode creationMode) {
        this.creationMode = creationMode;
    }

    public PitchEntity getPitch() {
        return pitch;
    }

    public void setPitch(PitchEntity pitch) {
        this.pitch = pitch;
    }
}
