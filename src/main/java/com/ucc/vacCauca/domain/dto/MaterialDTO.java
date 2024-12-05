package com.ucc.vacCauca.domain.dto;

import com.ucc.vacCauca.domain.entity.AbstractEntity;

import java.math.BigDecimal;

public class MaterialDTO extends AbstractEntity {

    private String name;
    private BigDecimal price;
    private BigDecimal quantityMeters;
    private BigDecimal quantityUsed;
    private BigDecimal valueMeterOrUnit;
    private BigDecimal totalPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantityMeters() {
        return quantityMeters;
    }

    public void setQuantityMeters(BigDecimal quantityMeters) {
        this.quantityMeters = quantityMeters;
    }

    public BigDecimal getValueMeterOrUnit() {
        return valueMeterOrUnit;
    }

    public void setValueMeterOrUnit(BigDecimal valueMeterOrUnit) {
        this.valueMeterOrUnit = valueMeterOrUnit;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(BigDecimal quantityUsed) {
        this.quantityUsed = quantityUsed;
    }
}
