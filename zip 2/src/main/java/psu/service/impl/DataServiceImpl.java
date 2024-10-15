package psu.service.impl;

import psu.repository.DataRepository;
import psu.model.PasswordEntry;
import psu.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DataServiceImpl(DataRepository dataRepository) {
        //this.dataRepository = new DataRepositoryImpl(); // developer managed instance
        this.dataRepository = dataRepository;   // Spring managed instance
    }

    @Override
    public List<PasswordEntry> getPasswordEntries() {
        return dataRepository.getPasswordEntries();
    }

    @Override
    public List<PasswordEntry> getFilteredPasswordEntries(String search) {
        var data = dataRepository.getPasswordEntries();
        return data.stream()
                .filter(entry -> entry.username().contains(search) || entry.siteName().contains(search))
                .toList();
    }

    @Override
    public void addPasswordEntry(PasswordEntry newEntry) {
        dataRepository.addEntry(newEntry);
    }

    @Override
    public void deleteById(String id) {
        dataRepository.deleteById(id);
    }

    @Override
    public PasswordEntry getPasswordEntryById(String id) {
        return dataRepository.getPasswordEntryById(id);
    }
}
