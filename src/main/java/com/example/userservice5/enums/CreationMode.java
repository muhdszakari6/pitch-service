package com.example.userservice5.enums;

public enum CreationMode {
    WEEKLY("Weekly"),
    DAILY("Daily");

    private final String displayName;

    CreationMode(String displayName) {
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
