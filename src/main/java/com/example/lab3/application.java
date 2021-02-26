package com.example.lab3;

import com.sun.jndi.cosnaming.IiopUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class application {

    private static final Logger log = LoggerFactory.getLogger(application.class);

    public static void main(String[] args) {
        SpringApplication.run(application.class, args);
    }

    @Bean
    public CommandLineRunner demo(BuddyRepo repo, AddressBookRepo aRepo) {
        return (args) -> {
            // save a few customers
            ArrayList<BuddyInfo> list = new ArrayList<BuddyInfo>();
            BuddyInfo b = new BuddyInfo("Jack", 18);
            BuddyInfo b2 = new BuddyInfo("Andrew", 90);
            repo.save(b);
            repo.save(b2);
            list.add(b);
            list.add(b2);

            AddressBook book = new AddressBook();
            book.setName("Book1");
            book.setBuddy(list);
            aRepo.save(book);


            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (BuddyInfo x : repo.findAll()) {
                log.info("Buddy: " + x.getId() + " | " + x.getName() + " | " + x.getAge() + "");
            }
            log.info("");

            for (AddressBook x : aRepo.findAll()) {
                log.info("AddressBook: " + x.getId());
            }
            log.info("");

        };
    }

}