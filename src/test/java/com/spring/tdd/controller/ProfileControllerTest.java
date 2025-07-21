package com.spring.tdd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.jayway.jsonpath.JsonPath;
import com.spring.tdd.repository.ProfileRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRepository profileRepository;

    @BeforeEach
    void cleanUp() {
        profileRepository.deleteAll();
    }

    @Test
    void shouldGetAllProfiles() throws Exception {
        mockMvc.perform(post("/api/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Charles Lee\",\"age\":20}"))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane Smith\",\"age\":25}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/profiles")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Charles Lee"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].age").value(25));
    }

    @Test
    void shouldCreateProfile() throws Exception {
        mockMvc.perform(post("/api/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"age\":30}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }
    
    @Test
    void shouldGetProfile() throws Exception{
        String responseBody = mockMvc.perform(post("/api/profiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Suki Hashimoto\",\"age\":10}"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Number idNumber = JsonPath.read(responseBody, "$.id");
        Long id = idNumber.longValue();

        mockMvc.perform(get("/api/profiles/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Suki Hashimoto"))
                .andExpect(jsonPath("$.age").value(10));
    }

}