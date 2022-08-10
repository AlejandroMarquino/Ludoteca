package com.ccsw.tutorial.author.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// AMF

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nationality")
    private String nationality;

    // @return id

    public Long getId() {

        return this.id;
    }

    // @param nuevo id para el valor {@link #getid}.

    public void setId(Long id) {

        this.id = id;
    }

    // @return nombre

    public String getName() {

        return this.name;
    }

    // @param nuevo nombre para el valor {@link #getname}.

    public void setName(String name) {

        this.name = name;
    }

    // @return nacionalidad

    public String getNationality() {

        return this.nationality;
    }

    // @param nueva nacionalidad para el valor {@link #getnationality}.
    public void setNationality(String nationality) {

        this.nationality = nationality;
    }

}
