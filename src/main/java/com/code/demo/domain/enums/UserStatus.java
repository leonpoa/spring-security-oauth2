package com.code.demo.domain.enums;

import com.code.demo.common.Constants;

public enum UserStatus {
    ACTIVE("A", "Active"),
    PENDING("P", "Pending"),
    LOCKED("L", "Locked");

    private final String value;
    private final String description;

    UserStatus(final String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static UserStatus from(String value) {
        for (UserStatus status : values()) {
            if (status.getValue().equals(value)) return status;
        }
        throw new IllegalArgumentException(String.format(Constants.INVALID_ENUM_VALUE, value));
    }

}
