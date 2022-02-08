package com.mercadolibre.integrativeproject;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
@RunWith(SpringRunner.class)
@SpringBootTest

@ActiveProfiles("test")
class IntegrativeProjectBootcampW4G6ApplicationTests {

    @Autowired
    private org.springframework.core.env.Environment environment;

    @Test
    void contextLoads() {
        System.out.println(Arrays.toString(environment.getActiveProfiles()));
    }

}
