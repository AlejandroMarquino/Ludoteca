package com.ccsw.tutorial.leasing;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.config.mapper.BeanMapper;
import com.ccsw.tutorial.leasing.model.LeasingDto;
import com.ccsw.tutorial.leasing.model.LeasingSearchDto;

// clase controlador, acceso directo con el cliente, expone las operaciones a publicar y que el cliente (navegado) puede consumir.

@RequestMapping(value = "/leasing")
@RestController
@CrossOrigin(origins = "*")
public class LeasingController {

    @Autowired
    LeasingService leasingService;

    @Autowired
    BeanMapper beanMapper;

// método para mostrar un listado paginado 

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<LeasingDto> findPage(@RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "gameId", required = false) Long gameId,
            @RequestParam(value = "searchDate", required = false) Date searchDate, @RequestBody LeasingSearchDto dto) {

        return this.beanMapper.mapPage(this.leasingService.findPage(customerId, gameId, searchDate, dto),
                LeasingDto.class);
    }

// método para dar de alta o actualizar

    @ResponseBody
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public int save(@PathVariable(name = "id", required = false) Long id, @RequestBody LeasingDto data) {

        return this.leasingService.save(id, data);
    }

// método para borrar 

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.leasingService.delete(id);

    }

}
