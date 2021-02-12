package com.example.lab3;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BuddyRepo extends CrudRepository<BuddyInfo, Long> {

    List<BuddyInfo> findByName(String name);

    BuddyInfo findById(long id);
}