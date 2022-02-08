package com.mercadolibre.integrativeproject.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TokenAuthenticationServiceTest {


    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "cad25162b73e085c49ebca1be132cf93";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Test
        void doFilterTokenNotFound() {
            HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
            Authentication authentication = TokenAuthenticationService.getAuthentication(httpServletRequest);
            ArgumentCaptor<Long> idToDeleteRepositoryMethod = ArgumentCaptor.forClass(Long.class);
            assertNull(authentication);
    }

    @Test
    void doFilterUserFound() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        String token = Jwts.builder()
                .setSubject("teste@teste")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        when(httpServletRequest.getHeader(HEADER_STRING)).thenReturn(token);

        Authentication authentication = TokenAuthenticationService.getAuthentication(httpServletRequest);
        assertNotNull(authentication);
    }

    @Test
    void responseWithToken() {
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();

        TokenAuthenticationService.addAuthentication(httpServletResponse, "teste@teste");

        String token = httpServletResponse.getHeader(HEADER_STRING);

        assertNotNull(token);
    }
}