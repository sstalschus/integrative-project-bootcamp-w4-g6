package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.User;
import com.mercadolibre.integrativeproject.repositories.UserRepository;
import com.mercadolibre.integrativeproject.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        user.setPassword(PasswordUtils.encoder(user.getPassword()));
        return userRepository.save(user);
    }
}
