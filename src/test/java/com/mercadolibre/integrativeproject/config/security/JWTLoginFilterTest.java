package com.mercadolibre.integrativeproject.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.integrativeproject.entities.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class JWTLoginFilterTest {

    @Disabled
    @Test
    void attemptAuthentication() throws IOException, ServletException {
        User user = User.builder()
                .id(1L)
                .name("teste")
                .email("teste@teste")
                .password("123").build();

        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();

        JWTLoginFilter mock = Mockito.mock(JWTLoginFilter.class);
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        InputStream anyInputStream = new ByteArrayInputStream("{email:\"teste@tesste\", password:\"123\"}".getBytes());
        Mockito.when(request.getInputStream()).thenReturn((ServletInputStream) anyInputStream);

        Authentication authentication = mock.attemptAuthentication(request, response);

        assertNotNull(authentication);
    }

    @Test
    void successfulAuthentication() {
    }
}