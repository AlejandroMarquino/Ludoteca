package com.ccsw.tutorial.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;

// @author AMF

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

// Método para recuperar todas las Category @return

    @Override
    public List<Category> findAll() {

        return (List<Category>) this.categoryRepository.findAll();
    }

    // Método para recuperar todas las ID @return

    @Override
    public Category get(Long id) {

        return this.categoryRepository.findById(id).orElse(null);
    }

    // Método para crear o actualizar una Category @param dto @return

    @Override
    public void save(Long id, CategoryDto dto) {

        Category categoria = null;

        if (id == null)
            categoria = new Category();
        else
            categoria = this.categoryRepository.findById(id).orElse(null);

        categoria.setName(dto.getName());

        this.categoryRepository.save(categoria);
    }

    // Método para borrar una Category * @param id
    @Override
    public void delete(Long id) {

        this.categoryRepository.deleteById(id);

    }
}