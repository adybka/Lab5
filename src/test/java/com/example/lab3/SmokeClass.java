package com.example.lab3;

import com.example.lab3.AddressBookController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeClass {

    @Autowired
    private AddressBookController controller;
    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(controller).isNotNull();
    }




}
