package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Supplier;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.SupplierRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;

/** Service para a entidade de registro de Supplier.
 *
 * @author Jefferson Froes
 *
 * */
@Service
public class SupplierService implements ICrudServiceInterface<Supplier, Long> {

    SupplierRepository supplierRepository;

    AddressService addressService;

    public SupplierService(SupplierRepository supplierRepository, AddressService addressService) {
        this.supplierRepository = supplierRepository;
        this.addressService = addressService;
    }

    /**
     * Método usado para criar um novo registro de Supplier.
     *
     * @param supplier - Registro de supplier.
     * @return Registro de supplier criado.
     * @author Jefferson Froes
     */
    @Override
    public Supplier create(Supplier supplier) {
        supplier.setAddress(addressService.create(supplier.getAddress()));
        return supplierRepository.save(supplier);
    }

    /**
     * Método usado para pegar um registro de supplier pelo ID.
     *
     * @param supplierId - Id do registro de supplier.
     * @return Registro de suppler criado.
     * @throws NotFoundException - por id não encontrado.
     * @author Jefferson Froes
     */
    @Override
    public Supplier getById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new NotFoundException("Id not Found"));
    }

    /**
     * Método usado para pegar todos os registros de supplier.
     *
     * @return Lista com os registros de supplier.
     * @author Jefferson Froes
     */
    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    /**
     * Método usado para atualizar o registro supplier.
     *
     * @param supplier - objeto que recebe os dados.- id do objeto a ser atualizado
     * @author Jefferson Froes
     */
    @Override
    public Supplier update(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    /**
     * Método usado para deletar o registro supplier.
     *
     * @param supplierId - id do objeto a ser deletado.
     * @author Jefferson Froes.
     */
    @Override
    public void delete(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
