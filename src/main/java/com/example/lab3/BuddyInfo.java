package com.example.lab3;

import javax.persistence.*;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public BuddyInfo(){
    }

    public BuddyInfo(String name, int age){
        this.name = name;
        this.age = age;
    }

}
