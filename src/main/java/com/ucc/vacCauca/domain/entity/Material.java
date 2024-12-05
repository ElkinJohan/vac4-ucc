package com.ucc.vacCauca.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Material extends AbstractEntity{

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity_meters", nullable = false)
    private BigDecimal quantityMeters;

    @Column(name = "quantity_used", nullable = false)
    private BigDecimal quantityUsed;

    @Column(name = "value_meter_or_unit", nullable = false)
    private BigDecimal valueMeterOrUnit;

    @Column(name = "total_price", nullable = false)
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
