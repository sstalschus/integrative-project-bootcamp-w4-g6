package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.InventaryRegisterRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IInventaryRegister;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service para a entidade de registro de estoque.
 *
 * @author Samuel Stalschus
 *
 * */
@Service
public class InventaryRegisterService implements IInventaryRegister<InventaryRegister, Long> {

    InventaryRegisterRepository inventaryRegisterRepository;

    public InventaryRegisterService(InventaryRegisterRepository inventaryRegisterRepository) {
        this.inventaryRegisterRepository = inventaryRegisterRepository;
    }

    /** Método usado para criar um novo registro de estoque.
     *
     * @author Samuel Stalschus
     *
     * @param  inventaryRegister - Registro de estoque.
     *
     * @return Registro de estoque criado
     *
     * */
    @Override
    public InventaryRegister create(InventaryRegister inventaryRegister){
        if(inventaryRegister.getBatch().getId() == null) throw new NotFoundException("Batch don't exists");
        if(inventaryRegister.getResponsible().getId() == null) throw new NotFoundException("Responsible don't exists");
        return inventaryRegisterRepository.save(inventaryRegister);
    }

    /** Método usado para pegar um registro de estoque pelo ID.
     *
     * @author Samuel Stalschus
     *
     * @param  id - Id do registro de estoque.
     *
     * @return Registro de estoque criado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public InventaryRegister findById(Long id) {
        return inventaryRegisterRepository.findById(id).orElseThrow(() -> new NotFoundException("Inventary register not found"));
    }

    /** Método usado para pegar todos os registros de estoque.
     *
     * @author Samuel Stalschus
     *
     * @return Lista com os registros de estoque
     *
     * */
    @Override
    public List<InventaryRegister> getAll() {
        return inventaryRegisterRepository.findAll();
    }
}
