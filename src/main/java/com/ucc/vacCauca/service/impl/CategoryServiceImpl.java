package com.ucc.vacCauca.service.impl;

import com.ucc.vacCauca.domain.dto.CategoryDTO;
import com.ucc.vacCauca.domain.entity.Category;
import com.ucc.vacCauca.domain.payload.CategoryForm;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import com.ucc.vacCauca.repository.CategoryRepository;
import com.ucc.vacCauca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAllActives(RegisterStatusEnum.ACTIVE);
    }

    @Override
    public List<Category> getAllCategoriesByName(String name) {
        return this.categoryRepository.findAllCategoriesByName(name, RegisterStatusEnum.ACTIVE);
    }

    @Override
    public CategoryDTO save(CategoryForm form) {
        Category category = new Category();

        if (form.getId() != null) {
            category.setId(form.getId());
        }

        category.setName(form.getName());
        category.setStatus(RegisterStatusEnum.ACTIVE);

        category = this.categoryRepository.save(category);

        CategoryDTO responseDTO = new CategoryDTO();

        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setStatus(category.getStatus());

        return responseDTO;
    }

    @Override
    public CategoryDTO update(CategoryForm form, Long id) {

        if (categoryRepository.existsById(id)) {
            return this.save(form);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {

        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setStatus(RegisterStatusEnum.INACTIVE);
            Category save = this.categoryRepository.save(category);
            return save.getId() != null;
        } else {
            return false;
        }
    }
}
