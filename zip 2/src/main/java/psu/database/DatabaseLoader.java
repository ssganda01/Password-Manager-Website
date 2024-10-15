package psu.database;

import psu.model.PasswordEntry;
import psu.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent>{

    private final DataRepository dataRepository;

    @Autowired
    public DatabaseLoader(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    //  this code will run on startup
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("*** DATABASE LOADER STARTING ***");

        var entry1 = new PasswordEntry(UUID.randomUUID().toString(), "Bobby123", "t0pS3cr3t", "Instagram", null, LocalDate.of(2021, 3, 1));
        var entry2 = new PasswordEntry(UUID.randomUUID().toString(), "gamer1997@gmail.com", "c0olGames$1", "Microsoft", null, LocalDate.of(2024, 1, 15));

        dataRepository.addEntry(entry1);
        dataRepository.addEntry(entry2);
    }
}
