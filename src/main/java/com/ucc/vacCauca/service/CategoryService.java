package com.ucc.vacCauca.service;

import com.ucc.vacCauca.domain.dto.CategoryDTO;
import com.ucc.vacCauca.domain.entity.Category;
import com.ucc.vacCauca.domain.payload.CategoryForm;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    List<Category> getAllCategoriesByName(String name);

    CategoryDTO save(CategoryForm form);

    CategoryDTO update(CategoryForm form, Long id);

    boolean delete(Long id);

}
