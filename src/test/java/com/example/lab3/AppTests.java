package com.example.lab3;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BuddyRepo buddyRepo;

    @Autowired
    AddressBookRepo addressBookRepoRepo;

    @Test
    @Order(1)
    public void addressBookShouldBeInital() throws Exception{
        String url = "/addressbooks";
        this.mockMvc.perform(get(url))
            .andDo(print())
            .andExpect(status().isOk());
        assertEquals(this.addressBookRepoRepo.count(), 1);
        assertEquals(this.buddyRepo.count(), 2);
    }

    @Test
    @Order(2)
    public void createNewBuddy() throws Exception{
        String url = "/buddyinfo";
        this.mockMvc.perform(post(url)
                .param("name", "Brock")
                .param("age", "20")
                .param("bookid", "3")
            ).andDo(print())
            .andExpect(status().is(200));
        assertNotNull(this.buddyRepo.findByName("Brock"));

    }

}