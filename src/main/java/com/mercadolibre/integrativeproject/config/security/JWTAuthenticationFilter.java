package com.mercadolibre.integrativeproject.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getHeader("origin").equals("http://localhost:63342") && httpRequest.getRequestURI().contains("/api/v1/warn") ) {
            UsernamePasswordAuthenticationToken websocket = new UsernamePasswordAuthenticationToken("websocket", null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(websocket);
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = TokenAuthenticationService
                .getAuthentication(httpRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
