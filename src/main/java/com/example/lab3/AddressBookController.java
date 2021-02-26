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
    public String createBook(Model model){
        model.addAttribute("newBook", new AddressBook());
        return "CreateBook";
    }

    @PostMapping("/addressbooks")
    public String newBook(@ModelAttribute AddressBook newBook, Model model){
        List<String> book = new ArrayList<String>();
        repository.save(newBook);
        book.add("Book Id Added: " + newBook.getId());
        model.addAttribute("list", book);
        return "CreateBook";
    }

    @GetMapping("/buddyinfo")
    public String createBuddy(Model model){
        model.addAttribute("newBud", new BuddyInfo());
        model.addAttribute("books", repository.findAll());
        return "ChooseBook";

    }

    @PostMapping("/buddyinfo")
    public String newBuddy(@ModelAttribute BuddyInfo newBud, @RequestParam(value = "bookId") long bid, Model model){
        AddressBook book = repository.findById(bid);
        List<BuddyInfo> buddies = new ArrayList<BuddyInfo>();
        buddyRepo.save(newBud);
        book.addBuddy(newBud);
        model.addAttribute("list", book.getBuddy());
        model.addAttribute("book", book);
        model.addAttribute("newBud", newBud);
        return "View";
    }

}