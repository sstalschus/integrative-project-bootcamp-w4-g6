package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.InboundOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InboundOrderController {

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return ResponseEntity.ok(inboundOrderDTO);
    }
}
