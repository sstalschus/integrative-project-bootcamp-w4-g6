package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.AddressRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IAddressService;
import org.springframework.stereotype.Service;

/** Service do Endereço
 *
 * @author Daniel Ramos, Jefferson Froes, Samuel Stalschus
 *
 * */
@Service
public class AddressService implements IAddressService<Address, Long> {

    AddressRepository addressRepository;

    /**
     *
     * Foi criado o construtor para promover o princípio da injeção de dependências
     *
     * */
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /** Método usado para criar um novo endereço
     *
     * @author Daniel Ramos, Jefferson Froes,Samuel Stalschus
     *
     * @param  address - Endereço
     *
     * @return Endereço
     *
     * */
    @Override
    public Address create(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    /** Método usado para obter o endereço pelo addressId.
     *
     * @author Daniel Ramos, Jefferson Froes, Samuel Stalschus
     *
     * @param  addressId - Id do endereço.
     *
     * @return Endereço que tenha o addressId informado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public Address getById(Long addressId) throws NotFoundException {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not Found"));
    }

    /**
     * Método usado para atualizar o registro do endereço.
     *
     * @param address - objeto que recebe os dados. O id do objeto a ser atualizado
     * @author Daniel Ramos, Jefferson Froes, Samuel Stalschus
     *
     */
    @Override
    public Address update(Address address) throws NotFoundException {
        Address addressId = getById(address.getId());
        return addressRepository.save(address);
    }
}
