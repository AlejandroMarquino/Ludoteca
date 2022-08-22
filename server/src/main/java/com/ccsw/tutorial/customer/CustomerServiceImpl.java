package com.ccsw.tutorial.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;

// @author AMF

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

// Método para recuperar todas las Category @return

    @Override
    public List<Customer> findAll() {

        return (List<Customer>) this.customerRepository.findAll();
    }

    // Método para recuperar todas las ID @return

    @Override
    public Customer get(Long id) {

        return this.customerRepository.findById(id).orElse(null);
    }

    // Método para crear o actualizar una Category @param dto @return

    @Override
    public void save(Long id, CustomerDto dto) {

        Customer customer = null;

        if (id == null)
            customer = new Customer();
        else
            customer = this.get(id);

        customer.setName(dto.getName());

        this.customerRepository.save(customer);
    }

    // Método para borrar un Customer * @param id
    @Override
    public void delete(Long id) {

        this.customerRepository.deleteById(id);

    }
}