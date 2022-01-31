package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService implements ICrudServiceInterface<Storage, Long> {
    @Override
    public Storage create(Storage storage) {
        return null;
    }

    @Override
    public Storage getById(Long aLong) {
        return null;
    }

    @Override
    public List<Storage> getAll() {
        return null;
    }

    @Override
    public Storage update(Storage storage) {
        return null;
    }

    @Override
    public void delete(Long t) {

    }

    public Storage getValidStorage(Long id) {
        Storage storage = getById(id);

        if (storage == null){
            throw new NotFoundException("Storage not found");
        }
        return storage;
    }

}
