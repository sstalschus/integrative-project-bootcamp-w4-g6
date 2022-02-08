package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.AdvertsCreateDTO;
import com.mercadolibre.integrativeproject.dtos.AdvertsDTO;
import com.mercadolibre.integrativeproject.entities.Adverts;
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
    public ResponseEntity<?> create(@RequestBody AdvertsCreateDTO advertsDTO){
        Adverts adverts = advertsService.create(advertsDTO.convert());
        return ResponseEntity.ok(AdvertsDTO.convert(adverts));
    }



    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(AdvertsDTO.convert(advertsService.getAll()));
    }

    /** Método usado para atualizar o anúncio pelo caminho /adverts/{id}.
     *
     * @author Daniel Ramos
     *
     * @return Anúncio
     *
     * */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody AdvertsCreateDTO advertsDTO) {
        advertsService.update(advertsDTO.convert());
        return ResponseEntity.status(204).body(null);
    }
}
