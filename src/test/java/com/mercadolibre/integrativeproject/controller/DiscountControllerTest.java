package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.config.security.TokenAuthenticationService;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.User;
import com.mercadolibre.integrativeproject.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DiscountControllerTest {

    private String  token = "";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    void create() throws Exception {

        User user = new User();
        user.setEmail("teste@teste1");
        user.setName("Teste Teste");
        user.setPassword("123");
        userService.create(user);
        token = TokenAuthenticationService.generateJWT(user.getUsername());

        MvcResult mvcResult = this.mockMvc.perform(post("/optimize-adverts")
                        .header("Authorization", token))
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    void listAll() throws Exception {
        User user = new User();
        user.setEmail("teste@teste2");
        user.setName("Teste Teste");
        user.setPassword("123");
        userService.create(user);
        token = TokenAuthenticationService.generateJWT(user.getUsername());

        MvcResult mvcResult = this.mockMvc.perform(get("/optimize-adverts")
                        .header("Authorization", token))
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    void testListAll() throws Exception {
        User user = new User();
        user.setEmail("teste@teste3");
        user.setName("Teste Teste");
        user.setPassword("123");
        userService.create(user);
        token = TokenAuthenticationService.generateJWT(user.getUsername());

        MvcResult mvcResult = this.mockMvc.perform(get("/optimize-adverts/1")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    void blockAdvert() throws Exception {
        User user = new User();
        user.setEmail("teste@teste4");
        user.setName("Teste Teste");
        user.setPassword("123");
        userService.create(user);
        token = TokenAuthenticationService.generateJWT(user.getUsername());

        MvcResult mvcResult = this.mockMvc.perform(put("/optimize-adverts/1")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }
}