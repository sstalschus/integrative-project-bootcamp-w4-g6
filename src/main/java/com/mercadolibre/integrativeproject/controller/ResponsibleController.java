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

    /** Método usado para criar um responsible.
     *
     * @author Jefferson Freos
     * @param responsibleDTO - recebe um responsibleDTO para converter em responsible.
     * @return retorna um responsible convertido em responsibleDTO
     *
     * */
    @PostMapping()
    public ResponseEntity<ResponsibleDTO> create(@Valid @RequestBody ResponsibleDTO responsibleDTO) {
    Responsible responsible = ResponsibleDTO.convert(responsibleDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponsibleDTO.convert(responsibleService.create(responsible)));
    }

    /** Método usado para deletar um responsible.
     *
     * @author Jefferson Freos
     * @param id - recebe o id de um responsible para ser deletado.
     *
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Responsible> delete(@Valid @PathVariable Long id){
        responsibleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    /** Método usado para buscar uma lista de responsible.
     *
     * @author Jefferson Freos
     * @return retorna uma lista de responsible convertida para responsibleDTO.
     *
     * */
    @GetMapping("/all")
    public ResponseEntity<List<ResponsibleDTO>> listAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responsibleService.getAll().stream().map(ResponsibleDTO::convert).collect(Collectors.toList()));
    }

    /** Método usado para buscar um responsible por id.
     *
     * @author Jefferson Freos
     * @param id - responsible id para ser buscado.
     * @return retorna um responsible convertida para responsibleDTO.
     *
     * */
    @GetMapping("/{id}")
    public ResponseEntity<ResponsibleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponsibleDTO.convert(responsibleService.getById(id)));
    }
}
