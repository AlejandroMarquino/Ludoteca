package com.ccsw.tutorial.author;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.author.model.AuthorSearchDto;

//@author AMF - Clase service.

public interface AuthorService {

    // Recupera un dato a través de su ID - implementado para el TEST
    Author get(Long id);

    // Método para recuperar un listado paginado de {@link
    // com.ccsw.tutorial.author.model.Author}
    // @param dto
    // @return
    Page<Author> findPage(AuthorSearchDto dto);

    // Método para crear o actualizar una Author @param dto @return
    void save(Long id, AuthorDto data);

    // método para eliminar un Author @param dto @return
    void delete(Long id);

}
