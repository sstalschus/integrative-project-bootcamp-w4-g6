package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.InboundOrderDTO;
import com.mercadolibre.integrativeproject.entities.InboundOrder;
import com.mercadolibre.integrativeproject.services.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InboundOrderController {

    @Autowired
    private InboundService inboundService;

    @GetMapping(value = "/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody InboundOrderDTO inboundOrderDTO) {
        InboundOrder inboundOrder = inboundService.create(inboundOrderDTO.convert());
        return ResponseEntity.ok(inboundOrder);
    }
}
