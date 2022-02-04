package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.StorageRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService implements IStorageService<Storage, Long> {

    StorageRepository storageRepository;

    AddressService addressService;

    public StorageService(StorageRepository storageRepository, AddressService addressService) {
        this.storageRepository = storageRepository;
        this.addressService = addressService;
    }

    @Override
    public Storage create(Storage storage) {
        storage.setAddress(addressService.create(storage.getAddress()));
        return storageRepository.save(storage);
    }

    @Override
    public Storage getById(Long storageId) {
        return storageRepository.findById(storageId).orElseThrow(() -> new NotFoundException("Storage not found"));
    }

    public List<Storage> getAll() {
        return storageRepository.findAll();
    }

    public Storage getValidStorage(Long id) {
        Storage storage = getById(id);
        if (storage == null) {
            throw new NotFoundException("Storage not found");
        }
        return storage;
    }
}
