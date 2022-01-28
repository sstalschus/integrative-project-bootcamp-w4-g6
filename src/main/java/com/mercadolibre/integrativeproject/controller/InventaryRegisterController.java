package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.InventaryRegisterDTO;
import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.services.InventaryRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventaryregister")
public class InventaryRegisterController {

    @Autowired
    InventaryRegisterService inventaryRegisterService;

    @PostMapping("")
    public ResponseEntity<InventaryRegisterDTO> create(@Valid @RequestBody InventaryRegisterDTO inventaryRegisterDTO) {
        InventaryRegister inventaryRegister = InventaryRegisterDTO.convert(inventaryRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                InventaryRegisterDTO.convert(inventaryRegisterService.create(inventaryRegister)));
    }

    @GetMapping("")
    public ResponseEntity<List<InventaryRegisterDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                inventaryRegisterService.getAll().stream().map(InventaryRegisterDTO::convert).collect(Collectors.toList())
        );
    }
}
