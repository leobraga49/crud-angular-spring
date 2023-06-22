package com.example.enums;

public enum Status {
    ACTIVE("Active"), INACTIVE("Inactive");

    Status(String value) {
        this.value = value;
    }

    private final String value;
    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return value;
    }
}
