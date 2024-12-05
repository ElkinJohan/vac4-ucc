package com.ucc.vacCauca.domain.dto;

import com.ucc.vacCauca.domain.entity.AbstractEntity;
import java.math.BigDecimal;
import java.util.List;

public class ProductDTO extends AbstractEntity {

    private String name;
    private String description;
    private BigDecimal priceBase;
    private BigDecimal porcent;
    private BigDecimal porcentApplied;
    private BigDecimal totalPrice;
    private CategoryDTO categoryDTO;
    private List<MaterialDTO> materialDTOS;

    public BigDecimal getPriceBase() {
        return priceBase;
    }

    public void setPriceBase(BigDecimal priceBase) {
        this.priceBase = priceBase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public List<MaterialDTO> getMaterialDTOS() {
        return materialDTOS;
    }

    public void setMaterialDTOS(List<MaterialDTO> materialDTOS) {
        this.materialDTOS = materialDTOS;
    }

    public BigDecimal getPorcent() {
        return porcent;
    }

    public void setPorcent(BigDecimal porcent) {
        this.porcent = porcent;
    }

    public BigDecimal getPorcentApplied() {
        return porcentApplied;
    }

    public void setPorcentApplied(BigDecimal porcentApplied) {
        this.porcentApplied = porcentApplied;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
