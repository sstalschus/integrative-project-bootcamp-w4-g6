package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.exceptions.MissingParamsException;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.StorageRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service para a entidade de registro de Supplier.
 *
 * @author Daniel Ramos, Jefferson Froes, Samuel Stalschus
 *
 * */
@Service
public class StorageService implements IStorageService<Storage, Long> {

    StorageRepository storageRepository;

    AddressService addressService;

    public StorageService(StorageRepository storageRepository, AddressService addressService) {
        this.storageRepository = storageRepository;
        this.addressService = addressService;
    }

    /** Método usado para criar um novo armazém
     *
     * @author Daniel Ramos, Jefferson Froes, Samuel Stalschus
     *
     * @param  storage - Armazém
     *
     * @return Armazém criado
     *
     * */
    @Override
    public Storage create(Storage storage) throws MissingParamsException {
        if(storage.getAddress() == null) throw new MissingParamsException("Missing params");
        storage.setAddress(addressService.create(storage.getAddress()));
        return storageRepository.save(storage);
    }

    /** Método usado para obter um Armazém.
     *
     * @author Daniel Ramos, Jefferson Froes
     *
     * @param  storageId - Id do storage.
     *
     * @return Armazém que tenha o storageId informado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public Storage getById(Long storageId) {
        return storageRepository.findById(storageId).orElseThrow(() -> new NotFoundException("Storage not found"));
    }

    /**
     * Método usado para pegar todos os registros do Storage.
     *
     * @author Daniel Ramos
     *
     * @return Lista com os registros do storage.
     *
     */
    public List<Storage> getAll() {
        return storageRepository.findAll();
    }

    /**
     * Método usado para obter a validação do armazém pelo id
     *
     * @param id objeto de busca do armazém
     * @author Lorraine Mendes
     *
     * @return storage
     *
     *  @throws NotFoundException
     *
     */
    public Storage getValidStorage(Long id) {
        return getById(id);
    }
}
