package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.UserCreateDTO;
import com.mercadolibre.integrativeproject.dtos.UserDTO;
import com.mercadolibre.integrativeproject.entities.User;
import com.mercadolibre.integrativeproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO userCreateDTO) {
        User user = userService.create(userCreateDTO.convert());
        return ResponseEntity.ok(new UserDTO(user));
    }
}
