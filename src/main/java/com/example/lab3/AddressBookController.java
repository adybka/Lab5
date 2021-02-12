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
        List<String> books = new ArrayList<String>();
        for(AddressBook book: repository.findAll()){
            books.add("Book: "+ book.getId());
        }
        model.addAttribute("list", books);
        return "View";
    }

    @PostMapping("/addressbooks")
    public String newBook(@RequestBody AddressBook newBook, Model model){
        List<String> book = new ArrayList<String>();
        repository.save(newBook);
        book.add("Book Id Added: " + newBook.getId());
        model.addAttribute("list", book);
        return "View";
    }

    @GetMapping("/buddyinfo")
    public String findBuddy(Model model){
        List<String> buddies = new ArrayList<String>();
        for(BuddyInfo b: buddyRepo.findAll()){
            buddies.add(b.getId() + " | " +b.getName() + " | " + b.getAge());
        }
        model.addAttribute("list", buddies);
        return "View";

    }

    @PostMapping("/buddyinfo")
    public String newBuddy(@RequestParam(value="name") String name,
                         @RequestParam(value="age") int age,
                         @RequestParam(value="bookid") int bid,
                         Model model){
        List<String> buddy = new ArrayList<String>();
        BuddyInfo b = new BuddyInfo(name, age);
        AddressBook a = repository.findById(bid);
        a.addBuddy(b);
        buddyRepo.save(b);
        buddy.add("BuddyAdded: " + b.getId() + " | " +b.getName() + " | " + b.getAge());
        model.addAttribute("list", buddy);
        return "View";
    }

}