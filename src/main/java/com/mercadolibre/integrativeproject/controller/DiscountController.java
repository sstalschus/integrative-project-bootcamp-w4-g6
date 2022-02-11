package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.DiscountDTO;
import com.mercadolibre.integrativeproject.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/** Controller de descontos, requisito 6 Samuel Stalschus
 *
 * @author Samuel Stalschus
 *
 * */
@RestController
@RequestMapping("/optimize-adverts")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    /** Método usado para obter a lista de descontos que precisam ser efetivados
     * Com base em uma analise feita
     *
     * @author Samuel Stalschus
     *
     * @return Lista de Descontos
     *
     * */
    @PostMapping("")
    public ResponseEntity<List<DiscountDTO>> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                discountService.createDiscount().stream().map(DiscountDTO::convert).collect(Collectors.toList())
        );
    }

    /** Método usado para retornar todos os descontos em anuncios.
     *
     * @author Samuel Stalschus
     *
     * @return Lista de Descontos
     *
     * */
    @GetMapping("")
    public ResponseEntity<List<DiscountDTO>> listAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                discountService.getAll().stream().map(DiscountDTO::convert).collect(Collectors.toList())
        );
    }

    /** Método usado para obter os descontos anteriores de um anuncio
     *
     * @author Samuel Stalschus
     *
     * @return Cliente
     *
     * */
    @GetMapping("/{advertId}")
    public ResponseEntity<List<DiscountDTO>> listAll(@PathVariable Long advertId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                discountService.getByAdvertsId(advertId).stream().map(DiscountDTO::convert).collect(Collectors.toList())
        );
    }

    /** Método usado para bloquear a alteração de valor de um anuncio
     *
     * @author Samuel Stalschus
     *
     * @param advertId - Id do anuncio
     *
     * @return Lista de descontos bloqueados
     *
     * */
    @PutMapping("/{advertId}")
    public ResponseEntity<List<DiscountDTO>> blockAdvert(@PathVariable Long advertId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                discountService.blockAdvert(advertId).stream().map(DiscountDTO::convert).collect(Collectors.toList())
        );
    }
}
