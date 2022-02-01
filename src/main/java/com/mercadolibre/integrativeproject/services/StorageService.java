package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Storage;
<<<<<<< Updated upstream
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
=======
import com.mercadolibre.integrativeproject.repositories.StorageRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
<<<<<<< Updated upstream
public class StorageService implements ICrudServiceInterface<Storage, Long> {
=======
public class StorageService implements IStorageService, ICrudServiceInterface<Storage, Long> {
    @Autowired
    private StorageRepository storageRepository;

>>>>>>> Stashed changes
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

    @Override
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

    @Override
    public void delete(Long storageId) {
        try {
            storageRepository.deleteById(storageId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Storage getValidStorage(Long id) {
        Storage storage = getById(id);

        if (storage == null){
            throw new NotFoundException("Storage not found");
        }
        return storage;
    }

}
