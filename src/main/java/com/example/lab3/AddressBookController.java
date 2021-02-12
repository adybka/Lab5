package com.example.lab3;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    AddressBookRepo repository;
    @Autowired
    BuddyRepo buddyRepo;

    @GetMapping("/addressbooks")
    public String find(Model model){
        AddressBook book = repository.findById(3);
        List<String> buddies = new ArrayList<String>();
        for(BuddyInfo b: book.getBuddy()){
            buddies.add(b.getId() + " | " +b.getName() + " | " + b.getAge());
        }
        model.addAttribute("buddies", buddies);
        return "View";
    }

    @PostMapping("/addressbooks")
    public AddressBook newBook(@RequestBody AddressBook newBook){
        return repository.save(newBook);
    }

    @GetMapping("/buddyinfo")
    public String findBuddy(Model model){
        List<String> buddies = new ArrayList<String>();
        for(BuddyInfo b: buddyRepo.findAll()){
            buddies.add(b.getId() + " | " +b.getName() + " | " + b.getAge());
        }
        model.addAttribute("buddies", buddies);
        return "View";

    }

    @PostMapping("/buddyinfo")
    public void newBuddy(@RequestParam(value="name") String name,
                         @RequestParam(value="age") int age,
                         @RequestParam(value="bookid") int bid){
        BuddyInfo b = new BuddyInfo(name, age);
        AddressBook a = repository.findById(bid);
        a.addBuddy(b);
        buddyRepo.save(b);
    }

}