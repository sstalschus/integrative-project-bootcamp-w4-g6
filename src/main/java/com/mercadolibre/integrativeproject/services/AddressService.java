package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements ICrudServiceInterface<Address, Long> {

    @Override
    public Address create(Address address) {
        return null;
    }

    @Override
    public Address getById(Long aLong) {
        return null;
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public Address update(Address address) {
        return null;
    }

    @Override
    public void delete(Long t) {

    }

}
