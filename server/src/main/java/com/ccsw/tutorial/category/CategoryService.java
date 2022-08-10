package com.ccsw.tutorial.category;

import java.util.List;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;

// @author AMF

public interface CategoryService {

    // Método para mostrar todas las Category @return

    List<Category> findAll();

    // Método para mostrar todos los datos por ID

    Category get(Long id);

    // Método para crear o actualizar una Category @param dto @return

    void save(Long id, CategoryDto dto);

    // Método para borrar una Category @param id

    void delete(Long id);

}