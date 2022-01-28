package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    public ResponseEntity<?> create(){
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<?> getById(){
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(null);
    }
}
