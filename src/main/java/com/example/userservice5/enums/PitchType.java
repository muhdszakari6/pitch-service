package com.example.userservice5.enums;
public enum PitchType {
    HALF_PITCH("Half Pitch"),
    FULL_PITCH("Full Pitch");

    private final String displayName;

    PitchType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
