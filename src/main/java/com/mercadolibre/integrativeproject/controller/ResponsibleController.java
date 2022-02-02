package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.ResponsibleDTO;
import com.mercadolibre.integrativeproject.dtos.SectorDTO;
import com.mercadolibre.integrativeproject.dtos.SupplierDTO;
import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.entities.Supplier;
import com.mercadolibre.integrativeproject.services.ResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/** Controller de registro de responsible.
 *
 * @author Jefferson Froes
 *
 * */
@RestController
@RequestMapping("/responsible")
public class ResponsibleController {

    @Autowired
    ResponsibleService responsibleService;

    @PostMapping()
    public ResponseEntity<ResponsibleDTO> create(@Valid @RequestBody ResponsibleDTO responsibleDTO) {
    Responsible responsible = ResponsibleDTO.convert(responsibleDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponsibleDTO.convert(responsibleService.create(responsible, responsibleDTO.getSectorId())));
    }

    @PutMapping("/{responsibleId}/{sectorId}")
    public ResponseEntity<Responsible> update(@PathVariable Long responsibleId, @PathVariable Long sectorId) {
        responsibleService.update(responsibleId,sectorId);
        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Responsible> delete(@Valid @PathVariable Long id){
        responsibleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponsibleDTO>> listAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responsibleService.getAll().stream().map(ResponsibleDTO::convert).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsibleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponsibleDTO.convert(responsibleService.getById(id)));
    }
}
