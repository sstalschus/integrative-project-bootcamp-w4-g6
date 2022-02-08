package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.config.security.TokenAuthenticationService;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.entities.User;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.BatchService;
import com.mercadolibre.integrativeproject.services.ProductService;
import com.mercadolibre.integrativeproject.services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BatchControllerTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private UserService userService;

    private String  token = "";

    private Batch batch;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws JSONException, InterruptedException {
        Product product = Product.builder()
                .name("Test Product")
                .volumn(15.9)
                .category(StorageType.FF)
                .build();

        Batch batch = new Batch();
        batch
                .setId(1L)
                .setProduct(product)
                .setInitialQuantity(100L)
                .setQuantity(100L)
                .setCurrentTemperature(4.1)
                .setMinimumTemperature(5.1)
                .setBrand("Teste")
                .setExpirationDate(Timestamp.valueOf("2022-10-21 13:28:06"))
                .setFabricationDate(Timestamp.valueOf("2021-10-21 13:28:06"))
                .setPricePerUnit(BigDecimal.valueOf(12.55));

        Batch batch2 = new Batch();
        batch2
                .setId(2L)
                .setProduct(product)
                .setInitialQuantity(100L)
                .setQuantity(100L)
                .setCurrentTemperature(4.1)
                .setMinimumTemperature(5.1)
                .setBrand("Teste")
                .setExpirationDate(Timestamp.valueOf("2022-10-21 13:28:06"))
                .setFabricationDate(Timestamp.valueOf("2021-10-21 13:28:06"))
                .setPricePerUnit(BigDecimal.valueOf(12.55));



        productRepository.save(product);
        batchRepository.save(batch);
        batchRepository.save(batch2);

    }

    @Test
    void getById() throws Exception {

        User user = new User();
        user.setEmail("teste@teste");
        user.setName("Teste Teste");
        user.setPassword("123");
        userService.create(user);
        token = TokenAuthenticationService.generateJWT(user.getUsername());

        MvcResult mvcResult = this.mockMvc.perform(get("/batch/1")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    void getAll() throws Exception {

        User user = new  User();
        user.setEmail("teste@teste");
        user.setName("Teste Teste");
        user.setPassword("123");

        userService.create(user);
        token = TokenAuthenticationService.generateJWT(user.getUsername());

        MvcResult mvcResult = this.mockMvc.perform(get("/batch/list")
                .header("Authorization", token))
                .andExpect(status().is(200))
                .andReturn();
        Assertions.assertEquals("application/json",
                mvcResult.getResponse().getContentType());

    }
}