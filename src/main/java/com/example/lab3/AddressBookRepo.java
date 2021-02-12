package com.example.lab3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface AddressBookRepo extends CrudRepository <AddressBook, Long>{

    AddressBook findById (@Param("id") long id);
}