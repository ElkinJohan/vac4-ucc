package com.ucc.vacCauca.controller;

import com.ucc.vacCauca.common.GeneralBodyResponse;
import com.ucc.vacCauca.config.security.AuthenticatedUser;
import com.ucc.vacCauca.domain.dto.ProductDTO;
import com.ucc.vacCauca.domain.entity.Product;
import com.ucc.vacCauca.domain.payload.ProductForm;
import com.ucc.vacCauca.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("saveProduct")
    public ResponseEntity<GeneralBodyResponse<ProductDTO>> saveProduct(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                       @RequestBody ProductForm productForm) {
        try {
            ProductDTO product = this.productService.save(productForm);
            if (product.getId() != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(product, "create", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateProduct/{idProduct}")
    public ResponseEntity<GeneralBodyResponse<ProductDTO>> updateProduct(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                         @PathVariable("idProduct") Long idProduct,
                                                                         @RequestBody ProductForm productForm) {
        try {
            ProductDTO product = this.productService.update(productForm, idProduct);

            if (product != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(product, "update", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("allProducts")
    public ResponseEntity<GeneralBodyResponse<List<Product>>> getAllProducts() {
        try {
            List<Product> products = this.productService.getAllActives();

            if (!products.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(products, "get all", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("productsByNameLike")
    public ResponseEntity<GeneralBodyResponse<List<Product>>> getAllProductsByName(@RequestBody ProductForm productsByNameLike) {
        try {
            List<Product> objects = this.productService.getAllProductsByNameLike(productsByNameLike.getName());
            if (!objects.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(objects, "search products by name like", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "not found", null), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteProduct/{idProduct}")
    public ResponseEntity<GeneralBodyResponse> deleteInvoice(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                             @PathVariable("idProduct") Long idProduct) {
        try {
            if (idProduct != null && idProduct != 0) {
                this.productService.deleteById(idProduct);
                return new ResponseEntity<>(new GeneralBodyResponse<>(true, "deleted", null), (HttpStatus.OK));
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "no exist", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


}
