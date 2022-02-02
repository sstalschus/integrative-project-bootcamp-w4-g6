package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.CreateCustomerDTO;
import com.mercadolibre.integrativeproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/** Controller de clientes
 *
 * @author Samuel Stalschus
 *
 * */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /** Método usado para criar um cliente.
     *
     * @author Samuel Stalschus
     *
     * @return Cliente criado
     *
     * */
    @PostMapping("/")
    public ResponseEntity<CreateCustomerDTO> create(@Valid @RequestBody CreateCustomerDTO createCustomerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateCustomerDTO.convert(customerService.create(CreateCustomerDTO.convert(createCustomerDTO)))
        );
    }

    /** Método usado para retornar para o client a lista com todos os Clientes.
     *
     * @author Samuel Stalschus
     *
     * @return Lista de DTOs de clientes acoplado ao Response Entity
     *
     * */
    @GetMapping("/")
    public ResponseEntity<List<CreateCustomerDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                customerService.getAll().stream().map(CreateCustomerDTO::convert).collect(Collectors.toList())
        );
    }

    /** Método usado para obter um cliente pelo ID.
     *
     * @author Samuel Stalschus
     *
     * @return Cliente
     *
     * */
    @GetMapping("/{id}")
    public ResponseEntity<CreateCustomerDTO> listAll(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CreateCustomerDTO.convert(customerService.getById(id))
        );
    }
}
