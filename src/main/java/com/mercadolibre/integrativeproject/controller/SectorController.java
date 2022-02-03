package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.*;
import com.mercadolibre.integrativeproject.entities.ProductPerSector;
import com.mercadolibre.integrativeproject.entities.ProductPerStorage;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/** Controller de setores
 * @author Lorraine Mendes
 **/

@RestController
@RequestMapping("/sector")
public class SectorController {

    @Autowired
    SectorService sectorService;


    @PostMapping("")
    public ResponseEntity<SectorDTO> create(@Valid @RequestBody SectorDTO sectorDTO){
        Sector sector = sectorDTO.convert();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SectorDTO.convert(sectorService.create(sector)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(SectorDTO.convert(sectorService.getById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SectorDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                sectorService.getAll().stream().map(SectorDTO::convert).collect(Collectors.toList())
        );
    }

    @GetMapping(value="/product-list")
    public ResponseEntity<List<ProductPerStorageDTO>> getListProductsInStorages(@RequestParam Long productId, @RequestParam String ordination) {
        List<ProductPerStorage> productPerStorages = sectorService.listProductPerSectorOnAllStorage(productId, ordination);
        return ResponseEntity.ok(productPerStorages.stream()
                .map(ProductPerStorageDTO::convert)
                .collect(Collectors.toList())
        );
    }
}
