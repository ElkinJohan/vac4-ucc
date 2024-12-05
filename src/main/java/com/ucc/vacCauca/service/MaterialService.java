package com.ucc.vacCauca.service;

import com.ucc.vacCauca.domain.dto.MaterialDTO;
import com.ucc.vacCauca.domain.entity.Material;
import com.ucc.vacCauca.domain.payload.MaterialForm;

import java.util.List;

public interface MaterialService {

    List<Material> getAllMaterials();

    List<Material> getAllMaterialsByName(String materialName);

    MaterialDTO save(MaterialForm form);

    MaterialDTO update(MaterialForm form, Long id);

    boolean delete(Long id);

}
