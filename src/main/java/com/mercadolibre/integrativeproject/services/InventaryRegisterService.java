package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.repositories.InventaryRegisterRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IInventaryRegister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventaryRegisterService implements IInventaryRegister<InventaryRegister, Long> {

    InventaryRegisterRepository inventaryRegisterRepository;

    public InventaryRegisterService(InventaryRegisterRepository inventaryRegisterRepository) {
        this.inventaryRegisterRepository = inventaryRegisterRepository;
    }

    @Override
    public InventaryRegister create(InventaryRegister inventaryRegister) {
        return inventaryRegisterRepository.save(inventaryRegister);
    }

    @Override
    public InventaryRegister getById(Long id) {
        return inventaryRegisterRepository.getById(id);
    }

    @Override
    public List<InventaryRegister> getAll() {
        return inventaryRegisterRepository.findAll();
    }
}
