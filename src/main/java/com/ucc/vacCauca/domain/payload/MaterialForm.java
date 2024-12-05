package com.ucc.vacCauca.domain.payload;


import java.math.BigDecimal;

public class MaterialForm {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal quantityMeters;
    private BigDecimal quantityUsed;
    private Long action;//1 guardar 0 eliminar

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

    public BigDecimal getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(BigDecimal quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAction() {
        return action;
    }

    public void setAction(Long action) {
        this.action = action;
    }
}
