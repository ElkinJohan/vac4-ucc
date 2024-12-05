package com.ucc.vacCauca.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_invoice", schema = "projectvac")
public class ProductInvoice extends AbstractEntity {

    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @JoinColumn(name = "id_product", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

    @Column(name = "id_invoice", nullable = false)
    private Long idInvoice;

    @JoinColumn(name = "id_invoice", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Invoice invoice;

    @Column(name = "size_x", nullable = false)
    private BigDecimal sizeX;

    @Column(name = "size_y", nullable = false)
    private BigDecimal sizeY;

    @Column(name = "total_size_m2", nullable = false)
    private BigDecimal totalSizeM2;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "total_price_with_size", nullable = false)
    private BigDecimal totalPriceWithSize;

    public BigDecimal getTotalPriceWithSize() {
        return totalPriceWithSize;
    }

    public void setTotalPriceWithSize(BigDecimal totalPriceWithSize) {
        this.totalPriceWithSize = totalPriceWithSize;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalSizeM2() {
        return totalSizeM2;
    }

    public void setTotalSizeM2(BigDecimal totalSizeM2) {
        this.totalSizeM2 = totalSizeM2;
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

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
