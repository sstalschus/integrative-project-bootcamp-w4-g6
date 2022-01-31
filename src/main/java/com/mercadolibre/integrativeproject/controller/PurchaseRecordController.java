package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.InventaryRegisterDTO;
import com.mercadolibre.integrativeproject.dtos.PurchaseRecordDTO;
import com.mercadolibre.integrativeproject.entities.PurchaseRecord;
import com.mercadolibre.integrativeproject.services.PurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/** Controller para registro de compra
 *
 * @author Lorraine Mendes
 * */

@RestController
@RequestMapping("/pucharse")
public class PurchaseRecordController {

    @Autowired
    PurchaseRecordService purchaseRecordService;

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseRecordDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(PurchaseRecordDTO.convert(purchaseRecordService.getById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<PurchaseRecordDTO> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                (PurchaseRecordDTO) purchaseRecordService.getAll().stream().map(PurchaseRecordDTO::convert).collect(Collectors.toList())
        );
    }
}