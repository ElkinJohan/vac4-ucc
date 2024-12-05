package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.Material;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    //buscar materiales por nombre
    @Query(value = "select m " +
            "from Material m " +
            "where m.name like :materialName and m.status = :status")
    List<Material> findAllMaterialByName(@Param("materialName") String materialName,
                                         @Param("status") RegisterStatusEnum status);

    //buscar los materiales activos
    @Query(value = "select m " +
            "from Material m " +
            "where m.status = :status")
    List<Material> findAllActives(@Param("status") RegisterStatusEnum status);
}
