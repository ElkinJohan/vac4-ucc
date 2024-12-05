package com.ucc.vacCauca.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Category extends AbstractEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
