package com.mercadolibre.integrativeproject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventaryRegisterControllerTest extends ControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @Disabled("Desativado por enquanto pois precisamos implementar a autenticação para testes de integração")
    void getInventaryRegisterList() {
        ResponseEntity<String> responseEntity =
                this.testRestTemplate.exchange(
                        "/inventaryregister", HttpMethod.GET, this.getDefaultRequestEntity(), String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("[]", responseEntity.getBody());
    }
}
