package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.AddressRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IAddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class AddressService implements IAddressService<Address, Long> {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getById(Long addressId) {
        try {
            return addressRepository.findById(addressId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Address update(Address address) {
        try {
            return addressRepository.save(address);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}