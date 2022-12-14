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
import com.ccsw.tutorial.exception.CurrentlyInUseException;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

//@author AMF

@RequestMapping(value = "/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BeanMapper beanMapper;

    /**
     * Método para recuperar todos los
     * {@link com.ccsw.tutorial.customer.model.Customer}
     * 
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CustomerDto> findAll() {

        return this.beanMapper.mapList(this.customerService.findAll(), CustomerDto.class);
    }

    /**
     * Método para crear o actualizar un
     * {@link com.ccsw.tutorial.customer.model.Customer}
     * 
     * @param dto
     */
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CustomerDto dto)
            throws CurrentlyInUseException {

        this.customerService.save(id, dto);
    }

    /**
     * Método para borrar un {@link com.ccsw.tutorial.customer.model.Customer}
     * 
     * @param id
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.customerService.delete(id);
    }

}