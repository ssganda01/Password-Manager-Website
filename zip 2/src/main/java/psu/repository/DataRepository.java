package psu.repository;

import psu.model.PasswordEntry;

import java.util.List;

public interface DataRepository {
    List<PasswordEntry> getPasswordEntries();

    void addEntry(PasswordEntry passwordEntry);
    void deleteById(String id);

    PasswordEntry getPasswordEntryById(String id);
}
