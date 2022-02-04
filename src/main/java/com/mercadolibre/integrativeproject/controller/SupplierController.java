package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.SupplierDTO;
import com.mercadolibre.integrativeproject.entities.Supplier;
import com.mercadolibre.integrativeproject.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/** Controller de registro de supplier.
 *
 * @author Jefferson Froes
 *
 * */
@RestController
@RequestMapping("/supplierregister")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("")
    public ResponseEntity<SupplierDTO> create(@Valid @RequestBody SupplierDTO supplierDTO ) {
        Supplier supplier = SupplierDTO.convert(supplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SupplierDTO.convert(supplierService.create(supplier)));
    }

    @PutMapping("/")
    public ResponseEntity<SupplierDTO> update(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = SupplierDTO.convert(supplierDTO);
        supplierService.update(supplier);
        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDTO> delete(@Valid @PathVariable Long id){
        supplierService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupplierDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(supplierService.getAll().stream().map(SupplierDTO::convert).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SupplierDTO.convert(supplierService.getById(id)));
    }
}
