package com.ucc.vacCauca.controller;

import com.ucc.vacCauca.common.GeneralBodyResponse;
import com.ucc.vacCauca.config.security.AuthenticatedUser;
import com.ucc.vacCauca.domain.dto.MaterialDTO;
import com.ucc.vacCauca.domain.entity.Material;
import com.ucc.vacCauca.service.MaterialService;
import com.ucc.vacCauca.service.impl.MaterialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.ucc.vacCauca.domain.payload.MaterialForm;

import java.util.List;

@RestController
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping("saveMaterial")
    public ResponseEntity<GeneralBodyResponse<MaterialDTO>> saveMaterial(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                         @RequestBody MaterialForm materialForm) {
        try {
            MaterialDTO material = this.materialService.save(materialForm);
            if (material.getId() != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(material, "create", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateMaterial/{idMaterial}")
    public ResponseEntity<GeneralBodyResponse<MaterialDTO>> updateMaterial(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                           @PathVariable("idMaterial") Long idMaterial,
                                                                           @RequestBody MaterialForm materialForm) {
        try {
            MaterialDTO material = this.materialService.update(materialForm, idMaterial);

            if (material != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(material, "update", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllMaterials")
    public ResponseEntity<GeneralBodyResponse<List<Material>>> getAllMaterials() {
        try {
            List<Material> materials = this.materialService.getAllMaterials();

            if (!materials.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(materials, "get all", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("searchMaterialsByName")
    public ResponseEntity<GeneralBodyResponse<List<Material>>> searchMaterialsByName(@RequestBody MaterialForm searchMaterialsByName) {
        try {
            List<Material> objects = this.materialService.getAllMaterialsByName(searchMaterialsByName.getName());
            if (!objects.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(objects, "search material by name like", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "not found", null), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteMaterial/{idMaterial}")
    public ResponseEntity<GeneralBodyResponse> deleteMaterial(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                              @PathVariable("idMaterial") Long idMaterial) {
        try {
            if (idMaterial != null && idMaterial != 0) {
                this.materialService.delete(idMaterial);
                return new ResponseEntity<>(new GeneralBodyResponse<>(true, "deleted", null), (HttpStatus.OK));
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "no exist", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}




