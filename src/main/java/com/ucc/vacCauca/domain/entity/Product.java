package com.ucc.vacCauca.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
public class Product extends AbstractEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price_base", nullable = false)
    private BigDecimal priceBase;

    @Column(name = "porcent", nullable = false)
    private BigDecimal porcent;

    @Column(name = "porcent_apply", nullable = false)
    private BigDecimal porcentApplied;

    @Column(name = "total_price_porcent", nullable = false)
    private BigDecimal totalPrice;

    @JoinColumn(name = "id_category", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    @Column(name = "id_category", nullable = false)
    private Long idCategory;

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

    public BigDecimal getPriceBase() {
        return priceBase;
    }

    public void setPriceBase(BigDecimal priceBase) {
        this.priceBase = priceBase;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }
}
