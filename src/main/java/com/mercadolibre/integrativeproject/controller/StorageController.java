package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.StorageDTO;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    StorageService storageService;

    @PostMapping
    public ResponseEntity<StorageDTO> create(@Valid @RequestBody StorageDTO storageDTO) {
        Storage storage = storageDTO.convert();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);
    }

    @GetMapping
    public ResponseEntity<List<StorageDTO>> getAll() {
        List<Storage> storageAll = storageService.getAll();
        return
                ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }
}
