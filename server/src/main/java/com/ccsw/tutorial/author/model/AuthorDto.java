package com.ccsw.tutorial.author.model;

// AMF

public class AuthorDto {

    private Long id;

    private String name;

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
