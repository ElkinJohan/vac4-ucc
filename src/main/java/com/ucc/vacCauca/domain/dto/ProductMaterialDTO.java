package com.ucc.vacCauca.domain.dto;

import com.ucc.vacCauca.domain.entity.AbstractEntity;

public class ProductMaterialDTO extends AbstractEntity {

    private Long idProduct;
    private Long idMaterial;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }
}
