package com.ucc.vacCauca.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "product_material", schema = "projectvac")
public class ProductMaterial extends AbstractEntity{

    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @JoinColumn(name = "id_product", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

    @Column(name = "id_material", nullable = false)
    private Long idMaterial;

    @JoinColumn(name = "id_material", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Material material;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
