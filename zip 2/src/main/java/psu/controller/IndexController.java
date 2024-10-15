package psu.controller;

import psu.model.PasswordEntry;
import psu.service.DataService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

//@RestController
@Controller
public class IndexController {

    private final DataService dataService;

    @Autowired
    public IndexController(DataService dataService) {
        //this.dataService = new DataServiceImpl();   // developer managed instance
        this.dataService = dataService; // Spring managed instance
    }

    @GetMapping("/")
    public String index(Model model) {
        var entries = dataService.getPasswordEntries(); // query the database for entries
        model.addAttribute("entries", entries);
        return "index"; // Name of the template file (templates/index.html)
    }

    @GetMapping("/search")   // read access
    public String search(Model model, @RequestParam String query) {
        var entries = dataService.getFilteredPasswordEntries(query); // query the database for entries
        model.addAttribute("entries", entries);
        return "index"; // Name of the template file (templates/index.html)
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // templates/about.html
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";   // templates/contact.html
    }


    // Add password entry
    @PostMapping("/add")
    public String add(@RequestParam String siteName, @RequestParam String userName, @RequestParam String userPassword, Model model) {
//        dataService.addPasswordEntry(name, password);
//        return "redirect:/"; // redirect to the index page

        var newEntry = new PasswordEntry(UUID.randomUUID().toString(), userName, userPassword, siteName, LocalDate.now(), LocalDate.now());
        dataService.addPasswordEntry(newEntry);
        model.addAttribute("entries", dataService.getPasswordEntries());
        return "index";
    }

    //
    //  edit password entry
    @GetMapping("/edit/{id}")
    public String viewEditPage(@PathVariable String id, Model model) {
        //  get the entry
        //  pass the entry to the edit page
        var entry = dataService.getPasswordEntryById(id);
        if (entry == null) {
            return "redirect:/fail";
        }
        model.addAttribute("entry", entry);
        return "edit";
    }

    @PostMapping("/edit")
    public String submitEditForm(@RequestParam String id, @RequestParam String siteName, @RequestParam String userName, @RequestParam String userPassword, Model model) {
        //  get the entry
        //  update the entry
        //  redirect to the index page
        var entry = dataService.getPasswordEntryById(id);
        if (entry == null) {
            return "redirect:/fail";
        }

        // delete the old record and add a new record, record types are immutable
        dataService.deleteById(id);

        entry = new PasswordEntry(id, userName, userPassword, siteName, LocalDate.now(), LocalDate.now());
        dataService.addPasswordEntry(entry);
        model.addAttribute("entries", dataService.getPasswordEntries());
        return "redirect:/success";
    }

    //  delete password entry
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model) {

        var entry = dataService.getPasswordEntryById(id);
        if (entry == null) {
            return "redirect:/fail";
        }

        //  delete the entry
        //  redirect to the index page
        dataService.deleteById(id);
        model.addAttribute("entries", dataService.getPasswordEntries());
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("success", true);
        model.addAttribute("entries", dataService.getPasswordEntries());
        return "index";
    }

    @GetMapping("/fail")
    public String error(Model model) {
        model.addAttribute("error", true);
        model.addAttribute("entries", dataService.getPasswordEntries());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
