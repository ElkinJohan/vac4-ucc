package com.ucc.vacCauca.service;


import com.ucc.vacCauca.domain.dto.ProductDTO;
import com.ucc.vacCauca.domain.entity.Product;
import com.ucc.vacCauca.domain.payload.ProductForm;


import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllActives();

    List<Product> getAllProductsByNameLike(String name);

    ProductDTO save(ProductForm form);

    ProductDTO update(ProductForm form, Long id);

    boolean deleteById(Long id);

}


