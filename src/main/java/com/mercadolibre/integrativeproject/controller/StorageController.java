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
import java.util.stream.Stream;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    StorageService storageService;

    @PostMapping
    public ResponseEntity<StorageDTO> create(@Valid @RequestBody StorageDTO storageDTO) {
        Storage storage = storageService.create(storageDTO.convert());
        storageDTO.setId(storage.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(storageDTO);
    }

    @GetMapping
    public ResponseEntity<List<StorageDTO>> getAll() {
        List<Storage> storageAll = storageService.getAll();
        List<StorageDTO> storageDTOList = storageAll.stream().map(storage -> {
            StorageDTO storageDTO = new StorageDTO();
            return storageDTO.convert(storage);
        }).collect(Collectors.toList());
        return
                ResponseEntity
                .status(HttpStatus.OK)
                .body(storageDTOList);
    }
}
