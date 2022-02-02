package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.repositories.StorageRepository;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.services.interfaces.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService implements IStorageService<Storage, Long> {

    @Autowired
    private  StorageRepository storageRepository;
    @Override
    public Storage create(Storage storage) {
        try {
            return storageRepository.save(storage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Storage getById(Long storageId) {
        try {
            return storageRepository.findById(storageId).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Storage> getAll() {
        try {
            return storageRepository.findAll();
        } catch (Exception e)  {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Storage update(Storage storage) {
        try {
            return storageRepository.save(storage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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
