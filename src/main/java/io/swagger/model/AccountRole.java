package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    AccountRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(role);
    }

    @JsonCreator
    public static AccountRole fromValue(String text) {
        for (AccountRole b : AccountRole.values()) {
            if (String.valueOf(b.role).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
