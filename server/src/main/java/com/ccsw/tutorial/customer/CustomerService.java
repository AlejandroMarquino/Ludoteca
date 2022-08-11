package com.ccsw.tutorial.customer;

import java.util.List;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;

// @author AMF

public interface CustomerService {

    // Método para mostrar todas las Category @return

    List<Customer> findAll();

    // Método para mostrar todos los datos por ID

    Customer get(Long id);

    // Método para crear o actualizar una Category @param dto @return

    void save(Long id, CustomerDto dto);

    // Método para borrar una Category @param id

    void delete(Long id);

}