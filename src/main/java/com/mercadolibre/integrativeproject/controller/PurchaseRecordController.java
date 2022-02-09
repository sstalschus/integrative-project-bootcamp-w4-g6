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

    /** Método usado para obter um Registro de compra pelo ID.
     *
     * @author Lorraine Mendes
     *
     * @return Registro de compra
     *
     * */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseRecordDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(PurchaseRecordDTO.convert(purchaseRecordService.getById(id)));
    }

    /** Método usado para buscar um objeto de purchaseRecord.
     *
     * @author Lorraine Mendes
     * @return retorna um objeto de purchaseRecord convertida para PurchaseRecordDTO.
     *
     * */
    @GetMapping("/all")
    public ResponseEntity<List<PurchaseRecordDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                purchaseRecordService.getAll().stream().map(PurchaseRecordDTO::convert).collect(Collectors.toList())
        );
    }
}
