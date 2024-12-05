package com.ucc.vacCauca.service.impl;

import com.ucc.vacCauca.domain.dto.CategoryDTO;
import com.ucc.vacCauca.domain.dto.MaterialDTO;
import com.ucc.vacCauca.domain.dto.ProductDTO;
import com.ucc.vacCauca.domain.entity.Category;
import com.ucc.vacCauca.domain.entity.ProductMaterial;
import com.ucc.vacCauca.domain.entity.Material;
import com.ucc.vacCauca.domain.entity.Product;
import com.ucc.vacCauca.domain.payload.MaterialForm;
import com.ucc.vacCauca.domain.payload.ProductForm;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import com.ucc.vacCauca.repository.CategoryRepository;
import com.ucc.vacCauca.repository.ProductMaterialRepository;
import com.ucc.vacCauca.repository.MaterialRepository;
import com.ucc.vacCauca.repository.ProductRepository;
import com.ucc.vacCauca.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ProductMaterialRepository productMaterialRepository;

    @Override
    public List<Product> getAllActives() {
        return this.productRepository.findAllActives(RegisterStatusEnum.ACTIVE);
    }

    @Override
    public List<Product> getAllProductsByNameLike(String name) {
        return this.productRepository.findAllProductsByNameLike(name, RegisterStatusEnum.ACTIVE);
    }

    @Override
    public ProductDTO save(ProductForm form) {

        //validar si existen los materiales
        List<Material> materialListExist = new ArrayList<>();
        for (MaterialForm materialForm :
                form.getMateriaList()) {
            materialListExist.add(this.materialRepository.findById(materialForm.getId()).get());
        }

        //guardando producto
        Product product = new Product();

        if (form.getId() != null) {
            product.setId(form.getId());
        }

        product.setStatus(RegisterStatusEnum.ACTIVE);
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setIdCategory(form.getIdCategory());

        //sumando el precio de los materiales para traer el precio base
        product.setPriceBase(getTotalPriceBase(materialListExist));

        //precio de ganancia del porcentaje que le ponga el admin
        product.setPorcent(form.getPorcent());
        product.setPorcentApplied(this.getPorcentToApplied(form, product.getPriceBase()));

        //precio del producto con el porcentaje sumado
        product.setTotalPrice(this.getTotalPrice(product.getPriceBase(), product.getPorcentApplied()));

        product = this.productRepository.save(product);

        for (MaterialForm materialListForm : form.getMateriaList()) {

            if (materialListForm.getAction() == 1) {
                ProductMaterial productMaterial = new ProductMaterial();

                productMaterial.setStatus(RegisterStatusEnum.ACTIVE);
                productMaterial.setIdProduct(product.getId());
                productMaterial.setIdMaterial(materialListForm.getId());

                productMaterialRepository.save(productMaterial);
            } else {
                this.productMaterialRepository.deleteByIdMaterialIdProduct(materialListForm.getId(), product.getId());
            }
        }


        //respuestaDTO
        ProductDTO responseDTO = new ProductDTO();

        responseDTO.setId(product.getId());
        responseDTO.setStatus(product.getStatus());
        responseDTO.setName(product.getName());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setPriceBase(product.getPriceBase());
        responseDTO.setPorcent(product.getPorcent());
        responseDTO.setPorcentApplied(product.getPorcentApplied());
        responseDTO.setTotalPrice(product.getTotalPrice());

        Optional<Category> optionalCategory = categoryRepository.findById(form.getIdCategory());
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(optionalCategory.get().getId());
        categoryDTO.setStatus(optionalCategory.get().getStatus());
        categoryDTO.setName(optionalCategory.get().getName());

        responseDTO.setCategoryDTO(categoryDTO);

        List<MaterialDTO> materialDTOList = new ArrayList<>();
        for (int i = 0; i < materialListExist.size(); i++) {
            MaterialDTO materialDTO = new MaterialDTO();

            Material material = materialListExist.get(i);

            materialDTO.setId(material.getId());
            materialDTO.setStatus(material.getStatus());
            materialDTO.setName(material.getName());
            materialDTO.setPrice(material.getPrice());
            materialDTO.setQuantityMeters(material.getQuantityMeters());
            materialDTO.setQuantityUsed(material.getQuantityUsed());
            materialDTO.setValueMeterOrUnit(material.getValueMeterOrUnit());
            materialDTO.setTotalPrice(material.getTotalPrice());

            materialDTOList.add(materialDTO);
        }

        responseDTO.setMaterialDTOS(materialDTOList);

        return responseDTO;
    }

    private BigDecimal getTotalPrice(BigDecimal priceBase, BigDecimal porcentApplied) {
        BigDecimal totalPrice = new BigDecimal("0.0");
        totalPrice = priceBase.add(porcentApplied);
        return totalPrice;
    }

    private BigDecimal getPorcentToApplied(ProductForm formPorcent, BigDecimal priceBase) {
        BigDecimal porcent = formPorcent.getPorcent();
        return priceBase.multiply(porcent).divide(new BigDecimal(100), 2, RoundingMode.CEILING);

    }

    private BigDecimal getTotalPriceBase(List<Material> materialList) {
        BigDecimal totalPriceBase = new BigDecimal("0.0");
        BigDecimal priceMaterial;
        for (int i = 0; i < materialList.size(); i++) {
            priceMaterial = materialList.get(i).getTotalPrice();
            totalPriceBase = totalPriceBase.add(new BigDecimal(String.valueOf(priceMaterial)));

        }
        return totalPriceBase;
    }

    @Override
    public ProductDTO update(ProductForm form, Long id) {

        if (productRepository.existsById(id)) {
            return this.save(form);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStatus(RegisterStatusEnum.INACTIVE);

            List<ProductMaterial> gpList = this.productMaterialRepository.findAllByIdProductToChangeValue(optionalProduct.get().getId());
            for (int i = 0; i < gpList.size(); i++) {
                ProductMaterial gp = gpList.get(i);
                gp.setStatus(RegisterStatusEnum.INACTIVE);

                this.productMaterialRepository.save(gp);
            }

            Product save = this.productRepository.save(product);
            return save.getId() != null;
        } else {
            return false;
        }
    }
}


