package psu.service;

//import edu.psu.twopass.model.PasswordEntry;
import psu.model.PasswordEntry;

import java.util.List;

public interface DataService {
    List<PasswordEntry> getPasswordEntries();

    List<PasswordEntry> getFilteredPasswordEntries(String search);

    void addPasswordEntry(PasswordEntry newEntry);

    void deleteById(String id);

    PasswordEntry getPasswordEntryById(String id);
}
