package com.ucc.vacCauca.service.impl;

import com.ucc.vacCauca.domain.dto.MaterialDTO;
import com.ucc.vacCauca.domain.entity.Material;
import com.ucc.vacCauca.domain.payload.MaterialForm;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import com.ucc.vacCauca.repository.MaterialRepository;
import com.ucc.vacCauca.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public MaterialDTO save(MaterialForm form) {
        Material material = new Material();

        if (form.getId() != null) {
            material.setId(form.getId());
        }

        material.setName(form.getName());
        material.setStatus(RegisterStatusEnum.ACTIVE);
        material.setPrice(form.getPrice());
        material.setQuantityUsed(form.getQuantityUsed());
        material.setQuantityMeters(form.getQuantityMeters());
        material.setValueMeterOrUnit(toPriceUnit(form));
        material.setTotalPrice(toTotalPrice(form));

        material = this.materialRepository.save(material);

        MaterialDTO responseDTO = new MaterialDTO();

        responseDTO.setId(material.getId());
        responseDTO.setStatus(material.getStatus());
        responseDTO.setName(material.getName());
        responseDTO.setPrice(material.getPrice());
        responseDTO.setQuantityUsed(material.getQuantityUsed());
        responseDTO.setQuantityMeters(material.getQuantityMeters());
        responseDTO.setValueMeterOrUnit(material.getValueMeterOrUnit());
        responseDTO.setTotalPrice(material.getTotalPrice());

        return responseDTO;
    }

    @Override
    public MaterialDTO update(MaterialForm form, Long id) {

        if (materialRepository.existsById(id)) {
            return this.save(form);
        } else {
            return null;
        }
    }

    @Override
    public List<Material> getAllMaterials() {
        return this.materialRepository.findAllActives(RegisterStatusEnum.ACTIVE);
    }

    @Override
    public List<Material> getAllMaterialsByName(String materialName) {
        return this.materialRepository.findAllMaterialByName("%" + materialName + "%", RegisterStatusEnum.ACTIVE);
    }

    @Override
    public boolean delete(Long id) {

        Optional<Material> materialOptional = this.materialRepository.findById(id);
        if (materialOptional.isPresent()) {
            Material material = materialOptional.get();
            material.setStatus(RegisterStatusEnum.INACTIVE);
            Material save = this.materialRepository.save(material);
            return save.getId() != null;
        } else {
            return false;
        }
    }

    private BigDecimal toPriceUnit(MaterialForm form) {

        BigDecimal price = form.getPrice();
        BigDecimal quantityMeters = form.getQuantityMeters();

        BigDecimal priceUnit = price.divide(quantityMeters, 2, RoundingMode.CEILING);

        return priceUnit;
    }

    private BigDecimal toTotalPrice(MaterialForm form) {
        BigDecimal quantityUsed = form.getQuantityUsed();
        BigDecimal priceUnit = toPriceUnit(form);

        BigDecimal totalPrice = priceUnit.multiply(quantityUsed);

        return totalPrice;
    }


}
