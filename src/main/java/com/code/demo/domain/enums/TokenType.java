package com.code.demo.domain.enums;

import com.code.demo.common.Constants;

public enum TokenType {
    ACTIVATION("A", "Activation"),
    RECOVER("R", "Recovery");

    private final String value;
    private final String description;

    TokenType(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static TokenType from(String value) {
        for (TokenType tokenType : values()) {
            if (tokenType.getValue().equals(value)) return tokenType;
        }
        throw new IllegalArgumentException(String.format(Constants.INVALID_ENUM_VALUE, value));
    }

}
