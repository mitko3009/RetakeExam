package com.example.finalproject.web;

import com.example.finalproject.repository.ComputerRepository;
import com.example.finalproject.repository.LaptopRepository;
import com.example.finalproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class OffersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @AfterEach
    void tearDown() {
        computerRepository.deleteAll();
        laptopRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.
                perform(get("/offers/all").with(user("mitko").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("list-offers"));
    }
}
