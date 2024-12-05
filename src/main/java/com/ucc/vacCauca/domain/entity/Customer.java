package com.ucc.vacCauca.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends AbstractEntity {

    @Column(name = "home_address", nullable = false, length = 100)
    private String homeAddress;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "identification", nullable = false, unique = true)
    private Long identification;

    @Column(name = "phone", nullable = false)
    private Long phone;

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
