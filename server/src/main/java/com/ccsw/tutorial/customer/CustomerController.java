package com.ccsw.tutorial.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.customer.model.CustomerDto;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

// @author AMF - clase controlador 

@RequestMapping(value = "/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BeanMapper beanMapper;

    // Método para recuperar todas las {@link
    // com.ccsw.tutorial.category.model.Customer} @return

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CustomerDto> findAll() {

        return this.beanMapper.mapList(this.customerService.findAll(), CustomerDto.class);
    }

    // Método para crear o actualizar una Category @param dto @return

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CustomerDto dto) {

        this.customerService.save(id, dto);
    }

    // Método para borrar una Category @param id
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.customerService.delete(id);
    }
}