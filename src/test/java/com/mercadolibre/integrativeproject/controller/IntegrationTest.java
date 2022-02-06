package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.IntegrativeProjectBootcampW4G6Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IntegrativeProjectBootcampW4G6Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    protected IntegrationTest() { }

    @AfterEach
    protected void afterEach() {
//        RequestMockHolder.clear();
    }
}
