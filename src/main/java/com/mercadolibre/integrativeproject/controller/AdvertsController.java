package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.AdvertsDTO;
import com.mercadolibre.integrativeproject.services.AdvertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/adverts")
public class AdvertsController {


    @Autowired
    AdvertsService advertsService;

    /** Método usado para criar um Adverts.
     *
     * @author Daniel Ramos
     * @param advertsDTO - recebe um advertsDTO para converter em adverts
     * @return retorna um adverts convertido em AdvertsDTO
     *
     * */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdvertsDTO advertsDTO){
        return ResponseEntity.ok(null);
    }


    /** Método usado para obter um anúncio pelo ID.
     *
     * @author Daniel Ramos
     *
     * @return Anúncio
     *
     * */
    @GetMapping
    public ResponseEntity<?> getById(){
        return ResponseEntity.ok(null);
    }

    /** Método usado para atualizar o anúncio pelo caminho /adverts/{id}.
     *
     * @author Daniel Ramos
     *
     * @return Anúncio
     *
     * */
    @PutMapping("/adverts/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody AdvertsDTO advertsDTO) {
        advertsService.update(AdvertsDTO.convert(advertsDTO));
        return ResponseEntity.status(204).body(null);
    }
}
