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


    /** Método usado para criar um Supplier.
     *
     * @author Jefferson Freos
     * @param supplierDTO - recebe um supplierDTO para converter em supplier
     * @return retorna um supplier convertido em supplierDTO
     *
     * */
    @PostMapping("")
    public ResponseEntity<SupplierDTO> create(@Valid @RequestBody SupplierDTO supplierDTO ) {
        Supplier supplier = SupplierDTO.convert(supplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SupplierDTO.convert(supplierService.create(supplier)));
    }

    /** Método usado para criar um Supplier.
     *
     * @author Jefferson Freos
     * @param supplierDTO - recebe um supplierDTO para converter em supplier
     * @return retorna um supplier convertido em supplierDTO
     *
     * */
    @PutMapping("/")
    public ResponseEntity<SupplierDTO> update(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = SupplierDTO.convert(supplierDTO);
        supplierService.update(supplier);
        return ResponseEntity.status(204).body(null);
    }

    /** Método usado para deletar um Supplier.
     *
     * @author Jefferson Freos
     * @param id - recebe o id de um supplier para ser deletado.
     *
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDTO> delete(@Valid @PathVariable Long id){
        supplierService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /** Método usado para buscar uma lista de supplier.
     *
     * @author Jefferson Freos
     * @return retorna uma lista de supplier convertida para supplierDTO.
     *
     * */
    @GetMapping("/all")
    public ResponseEntity<List<SupplierDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(supplierService.getAll().stream().map(SupplierDTO::convert).collect(Collectors.toList()));
    }

    /** Método usado para buscar um supplier por id.
     *
     * @author Jefferson Freos
     * @param id - supplier id para ser buscado.
     * @return retorna um supplier convertida para supplierDTO.
     *
     * */
    @GetMapping(value = "/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SupplierDTO.convert(supplierService.getById(id)));
    }
}
