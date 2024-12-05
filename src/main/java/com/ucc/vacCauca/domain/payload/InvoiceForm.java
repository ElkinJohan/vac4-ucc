package com.ucc.vacCauca.domain.payload;

import com.ucc.vacCauca.enums.ProgressStatus;


import java.math.BigDecimal;
import java.util.List;


public class InvoiceForm {

    private Long id;
    private String status;
    private String date;
    private ProgressStatus progressStatus;//si
    private Long idCustomer;//si
    private BigDecimal numberInvoice;
    private Long idUser;//si
    private List<ProductForm> productList;//si

    public BigDecimal getNumberInvoice() {
        return numberInvoice;
    }

    public void setNumberInvoice(BigDecimal numberInvoice) {
        this.numberInvoice = numberInvoice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public List<ProductForm> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductForm> productList) {
        this.productList = productList;
    }

}
