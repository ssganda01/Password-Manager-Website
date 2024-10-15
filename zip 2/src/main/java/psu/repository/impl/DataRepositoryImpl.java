package psu.repository.impl;

//import edu.psu.twopass.model.PasswordEntry;
import psu.model.PasswordEntry;
import org.springframework.stereotype.Repository;
import psu.repository.DataRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepositoryImpl implements DataRepository {
    //  instance data
    private final List<PasswordEntry> passwordEntries;

    //  constructor
    public DataRepositoryImpl() {
        this.passwordEntries = new ArrayList<>();
    }

    @Override
    public List<PasswordEntry> getPasswordEntries() {
        return this.passwordEntries;
    }

    @Override
    public void addEntry(PasswordEntry passwordEntry) {
        // add the password entry to the list
        this.passwordEntries.add(passwordEntry);
    }

    @Override
    public void deleteById(String id) {
        //  find the entry with the given id
        var entry = this.passwordEntries.stream()
                .filter(e -> e.id().equals(id))
                .findFirst()
                .orElse(null);

        //  if entry is found, remove it
        if (entry != null) {
            this.passwordEntries.remove(entry);
        }
    }

    @Override
    public PasswordEntry getPasswordEntryById(String id) {
        //  find the entry with the given id
        return this.passwordEntries.stream()
                .filter(e -> e.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}

