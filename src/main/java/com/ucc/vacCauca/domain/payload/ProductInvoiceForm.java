package com.ucc.vacCauca.domain.payload;

import java.math.BigDecimal;

public class ProductInvoiceForm {

    private Long id;
    private String status;
    private Long idInvoice;
    private Long idProduct;
    private BigDecimal sizeX;
    private BigDecimal sizeY;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
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
}
