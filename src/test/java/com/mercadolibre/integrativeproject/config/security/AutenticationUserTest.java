package com.mercadolibre.integrativeproject.config.security;

import com.mercadolibre.integrativeproject.entities.User;
import com.mercadolibre.integrativeproject.repositories.UserRepository;
import com.mercadolibre.integrativeproject.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AutenticationUserTest {

    @Test
    void loadUserByUsernameUserFound() {
        String email = "teste@tete.com";

        User user = User.builder()
                .id(1L)
                .email("teste@teste.com")
                .name("teste")
                .password("123")
                .build();

        UserRepository userRepository = Mockito.mock(UserRepository.class);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(user);
        AutenticationUser autenticationUser = new AutenticationUser(userRepository);
        UserDetails userDetailsFound = autenticationUser.loadUserByUsername(email);
        assertEquals(user, userDetailsFound);
    }

    @Test
    void loadUserByUsernameUserNotFound() {
        String email = "teste@tete.com";


        UserRepository userRepository = Mockito.mock(UserRepository.class);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(null);
        AutenticationUser autenticationUser = new AutenticationUser(userRepository);
        assertThrowsExactly(UsernameNotFoundException.class, () -> autenticationUser.loadUserByUsername(email));
    }

    @Test
    void loadUserByUsername() {
    }
}