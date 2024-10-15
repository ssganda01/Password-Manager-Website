package psu.model;

import java.time.LocalDate;

public record PasswordEntry(String id, String username, String password, String siteName, LocalDate dateCreated,
                            LocalDate dateModified) {
    public PasswordEntry {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }
        if (siteName.isBlank()) {
            throw new IllegalArgumentException("Site name cannot be blank");
        }
    }
}
