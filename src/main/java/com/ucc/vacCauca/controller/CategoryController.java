package com.ucc.vacCauca.controller;

import com.ucc.vacCauca.common.GeneralBodyResponse;
import com.ucc.vacCauca.config.security.AuthenticatedUser;
import com.ucc.vacCauca.domain.dto.CategoryDTO;
import com.ucc.vacCauca.domain.entity.Category;
import com.ucc.vacCauca.domain.payload.CategoryForm;
import com.ucc.vacCauca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("saveCategory")
    public ResponseEntity<GeneralBodyResponse<CategoryDTO>> saveCategory(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                         @RequestBody CategoryForm categoryForm) {
        try {
            CategoryDTO category = this.categoryService.save(categoryForm);
            if (category.getId() != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(category, "create", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateCategory/{idCategory}")
    public ResponseEntity<GeneralBodyResponse<CategoryDTO>> updateCategory(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                           @PathVariable("idCategory") Long idCategory,
                                                                           @RequestBody CategoryForm categoryForm) {
        try {
            CategoryDTO category = this.categoryService.update(categoryForm, idCategory);

            if (category != null) {
                return new ResponseEntity<>(new GeneralBodyResponse<>(category, "update", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllCategories")
    public ResponseEntity<GeneralBodyResponse<List<Category>>> getAllCategories() {
        try {
            List<Category> categories = this.categoryService.getAllCategories();
            if (!categories.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(categories, "get all", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("searchAllCategoriesByName")
    public ResponseEntity<GeneralBodyResponse<List<Category>>> getAllCategoriesByName(@RequestBody CategoryForm form) {
        try {
            List<Category> categories = this.categoryService.getAllCategoriesByName(form.getName());
            if (!categories.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(categories, "search category by name", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteCategory/{idCategory}")
    public ResponseEntity<GeneralBodyResponse> deleteMaterial(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                              @PathVariable("idCategory") Long idCategory) {
        try {
            if (idCategory != null && idCategory != 0) {
                this.categoryService.delete(idCategory);
                return new ResponseEntity<>(new GeneralBodyResponse<>(true, "deleted", null), (HttpStatus.OK));
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "no exist", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
