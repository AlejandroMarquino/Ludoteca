package com.ccsw.tutorial.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.author.model.Author;

// @author AMF

public interface AuthorRepository extends CrudRepository<Author, Long> {

    // Método para recuperar un listado paginado de
    // {@linkcom.ccsw.tutorial.author.model.Author}
    // @param dto
    // @return

    Page<Author> findAll(Pageable pageable);

}
