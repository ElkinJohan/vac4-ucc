package com.ucc.vacCauca.domain.payload;

import com.ucc.vacCauca.domain.entity.AbstractEntity;

public class CustomerForm {

    private Long id;
    private String status;
    private String homeAddress;
    private String fullName;
    private String email;
    private Long identification;
    private Long phone;

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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
        this.identification = identification;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
