package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.AddressRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IAddressService;
import org.springframework.stereotype.Service;


@Service
public class AddressService implements IAddressService<Address, Long> {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address getById(Long addressId) throws NotFoundException {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not Found"));
    }

    @Override
    public Address update(Address address) throws NotFoundException {
        Address addressId = getById(address.getId());
        return addressRepository.save(address);
    }
}
