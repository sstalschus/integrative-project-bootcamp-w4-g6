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

/** Controller de registro de estoque
 *
 * @author Samuel Stalschus
 *
 * */
@RestController
@RequestMapping("/inventaryregister")
public class InventaryRegisterController {

    @Autowired
    InventaryRegisterService inventaryRegisterService;

    /** Método usado para criar um registro de estoque.
     *
     * @author Samuel Stalschus
     *
     * @return DTO do registro de estoque, acoplado ao ResponseEntity
     *
     * */
    @PostMapping("")
    public ResponseEntity<InventaryRegisterDTO> create(@Valid @RequestBody InventaryRegisterDTO inventaryRegisterDTO) {
        InventaryRegister inventaryRegister = InventaryRegisterDTO.convert(inventaryRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                InventaryRegisterDTO.convert(inventaryRegisterService.create(inventaryRegister)));
    }

    /** Método usado para retornar para o client a lista com todos os registros de estoque.
     *
     * @author Samuel Stalschus
     *
     * @return Lista de DTOs de registro de estoque acoplado ao ResponseEntity
     *
     * */
    @GetMapping("")
    public ResponseEntity<List<InventaryRegisterDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                inventaryRegisterService.getAll().stream().map(InventaryRegisterDTO::convert).collect(Collectors.toList())
        );
    }
}
