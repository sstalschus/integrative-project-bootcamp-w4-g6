package com.mercadolibre.integrativeproject.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class JWTAuthenticationFilterTest {

    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "cad25162b73e085c49ebca1be132cf93";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Test
    void doFilter() throws IOException, ServletException {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        MockFilterChain chain = new MockFilterChain();

        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        String token = Jwts.builder()
                .setSubject("teste@teste")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        when(httpServletRequest.getHeader(HEADER_STRING)).thenReturn(token);


        jwtAuthenticationFilter.doFilter(httpServletRequest, httpServletResponse, chain);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assertNotNull(authentication);
    }
}