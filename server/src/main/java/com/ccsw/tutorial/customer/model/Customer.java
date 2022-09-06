package com.ccsw.tutorial.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// @author AMF

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * 
     * Devuelve el id del cliente.
     * 
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * Establece el id de un cliente.
     * 
     * @param id Long Identificador del cliente.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * Devuelve el nombre de un cliente.
     * 
     * @return name
     */
    public String getName() {

        return this.name;
    }

    /**
     * Establece el nombre de un cliente.
     * 
     * @param name String Nombre completo del cliente.
     */
    public void setName(String name) {

        this.name = name;
    }

}