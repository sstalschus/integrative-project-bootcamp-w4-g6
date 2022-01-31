package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.IStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService implements IStorageService, ICrudServiceInterface<Storage, Long> {
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

}
