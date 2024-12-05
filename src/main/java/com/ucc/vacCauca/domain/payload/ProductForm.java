package com.ucc.vacCauca.domain.payload;

import java.math.BigDecimal;
import java.util.List;

public class ProductForm {

    private Long id;
    private String name;
    private String description;
    private BigDecimal porcent;
    private Long idCategory;
    private List<MaterialForm> materiaList;
    private Long action;
    private BigDecimal sizeX;
    private BigDecimal sizeY;
    private BigDecimal quantity;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSizeX() {
        return sizeX;
    }

    public void setSizeX(BigDecimal sizeX) {
        this.sizeX = sizeX;
    }

    public BigDecimal getSizeY() {
        return sizeY;
    }

    public void setSizeY(BigDecimal sizeY) {
        this.sizeY = sizeY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public List<MaterialForm> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<MaterialForm> materiaList) {
        this.materiaList = materiaList;
    }

    public BigDecimal getPorcent() {
        return porcent;
    }

    public void setPorcent(BigDecimal porcent) {
        this.porcent = porcent;
    }

    public Long getAction() {
        return action;
    }

    public void setAction(Long action) {
        this.action = action;
    }
}
