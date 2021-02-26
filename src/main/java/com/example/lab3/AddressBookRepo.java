package com.example.lab3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface AddressBookRepo extends CrudRepository <AddressBook, Long>{

    AddressBook findById (long id);

    AddressBook findByName (String name);
}