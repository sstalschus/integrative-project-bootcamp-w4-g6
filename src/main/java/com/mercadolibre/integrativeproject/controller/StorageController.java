package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.StorageDTO;
import com.mercadolibre.integrativeproject.entities.Storage;
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
        Storage storage = StorageDTO.convert(storageDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StorageDTO.convert(storageService.createStorage(storage)));
    }

    @GetMapping
    public ResponseEntity<List<StorageDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storageService.getAllStorages().stream().map(StorageDTO::convert).collect(Collectors.toList()));
    }
}
