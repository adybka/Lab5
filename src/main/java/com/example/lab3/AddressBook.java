package com.example.lab3;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Entity
public class AddressBook {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany
    List<BuddyInfo> buddyCollection;

    public Long getId() {
        return id;
    }


    public Collection<BuddyInfo> getBuddy() {
        return buddyCollection;
    }

    public void setBuddy(Collection<BuddyInfo> buddies){
        this.buddyCollection = (List<BuddyInfo>) buddies;
    }

    public void addBuddy(BuddyInfo b){
        buddyCollection.add(b);
    }

    public AddressBook() {
        buddyCollection = new ArrayList<BuddyInfo>();
    }
    public AddressBook(long id) {
        this.id = id;
        buddyCollection = new ArrayList<BuddyInfo>();
    }



}
