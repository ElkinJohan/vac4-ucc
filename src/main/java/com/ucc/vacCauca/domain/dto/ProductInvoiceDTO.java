package com.ucc.vacCauca.domain.dto;

import com.ucc.vacCauca.domain.entity.AbstractEntity;

import java.math.BigDecimal;


public class ProductInvoiceDTO extends AbstractEntity {

    private Long idProduct;
    private Long idInvoice;
    private BigDecimal sizeX;
    private BigDecimal sizeY;

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

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }
}
