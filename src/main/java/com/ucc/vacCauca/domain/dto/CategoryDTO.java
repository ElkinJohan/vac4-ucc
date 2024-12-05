package com.ucc.vacCauca.domain.dto;

import com.ucc.vacCauca.domain.entity.AbstractEntity;

public class CategoryDTO extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
