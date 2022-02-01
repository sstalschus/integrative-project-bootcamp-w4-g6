package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Supplier;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.SupplierRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** Service para a entidade de registro de Supplier.
 *
 * @author Jefferson Froes
 *
 * */
@Service
public class SupplierService implements ICrudServiceInterface<Supplier, Long> {

    SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    /** Método usado para criar um novo registro de Supplier.
     *
     * @author Jefferson Froes
     *
     * @param  supplier - Registro de supplier.
     *
     * @return Registro de supplier criado.
     *
     * */
    @Override
    public Supplier create(Supplier supplier) {
        try{
            return supplierRepository.save(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Método usado para pegar um registro de supplier pelo ID.
     *
     * @author Jefferson Froes
     *
     * @param supplierId - Id do registro de supplier.
     *
     * @return Registro de suppler criado.
     *
     * @throws NotFoundException - por id não encontrado.
     *
     * */
    @Override
    public Supplier getById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new NotFoundException("Id not Found"));
    }

    /** Método usado para pegar todos os registros de supplier.
     *
     * @author Jefferson Froes
     *
     * @return Lista com os registros de supplier.
     *
     * */
    @Override
    public List<Supplier> getAll() {
        try {
            return supplierRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /** Método usado para atualizar o registro supplier.
     *
     * @author Jefferson Froes
     *
     * @param supplier - objeto que recebe os dados.
     *- id do objeto a ser atualizado
     * */
    @Override
    public Supplier update(Supplier supplier) {
        Supplier supplierSaved = getById(supplier.getId());
        return supplierRepository.setSupplierInfoById(supplier.getName(), supplier.getCnpj(),supplierSaved.getId());
    }

    /** Método usado para deletar o registro supplier.
     *
     * @author Jefferson Froes.
     *
     * @param supplierId - id do objeto a ser deletado.
     *
     * */
    @Override
    public void delete(Long supplierId) {
        try {
            supplierRepository.deleteById(supplierId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
