package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Supplier;
import com.mercadolibre.integrativeproject.repositories.SupplierRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService implements ICrudServiceInterface<Supplier, Long> {

    @Autowired
     private SupplierRepository supplierRepository;

    @Override
    public Supplier create(Supplier supplier) {
        try{
            return supplierRepository.save(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Supplier getById(Long supplierId) {
        try {
            return supplierRepository.findById(supplierId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Supplier> getAll() {
        try {
            return supplierRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Supplier update(Supplier supplier) {
        try {
            return supplierRepository.save(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void delete(Long supplierId) {
        try {
            supplierRepository.deleteById(supplierId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
