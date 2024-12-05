package com.ucc.vacCauca.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucc.vacCauca.enums.ProgressStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Invoice extends AbstractEntity {

    @Column(name = "number_invoice", nullable = false)
    private Long numberInvoice;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "progress_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgressStatus progressStatus;

    @JoinColumn(name = "id_customer", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer;

    @Column(name = "id_customer", nullable = false)
    private Long idCustomer;

    @JoinColumn(name = "id_user", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Column(name = "id_user", nullable = false)
    private Long idUser;

    public Long getNumberInvoice() {
        return numberInvoice;
    }

    public void setNumberInvoice(Long numberInvoice) {
        this.numberInvoice = numberInvoice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
