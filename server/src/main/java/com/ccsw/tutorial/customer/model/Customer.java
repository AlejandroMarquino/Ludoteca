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

    // Creamos el identificador del cliente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Metodo @return para obtener el id del cliente
    public Long getId() {
        return this.id; // this.id 08.11.22
    }

    // @param para setear el id del cliente
    public void setId(Long id) {
        this.id = id;
    }

    // Método @retunr que devuelve nombre del cliente
    public String getName() {
        return this.name;
    }

    // @param para setear el nombre del cliente
    public void setName(String name) {
        this.name = name;
    }

}
