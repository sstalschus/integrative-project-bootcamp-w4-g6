package com.mercadolibre.integrativeproject.services;


import com.mercadolibre.integrativeproject.entities.Customer;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.CustomerRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICustomerService;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service de clientes
 *
 * @author Samuel Stalschus
 *
 * */
@Service
public class CustomerService implements ICustomerService<Customer, Long> {

    CustomerRepository customerRepository;

    /**
     *
     * Foi criado o construtor para promover o princípio da injeção de dependências
     *
     * */
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /** Método usado para criar um novo cliente
     *
     * @author Samuel Stalschus
     *
     * @param  customer - Cliente
     *
     * @return Cliente criado
     *
     * */
    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    /** Método usado para obter um Cliente pelo ID.
     *
     * @author Samuel Stalschus
     *
     * @param  id - Id do Cliente.
     *
     * @return Cliente que tenha o ID informado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public Customer getById(Long id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    /** Método usado para obter todos os clientes.
     *
     * @author Samuel Stalschus
     *
     * @return Lista com todos os clientes
     *
     * */
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
