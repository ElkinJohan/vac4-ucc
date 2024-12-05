package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.Category;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Seleccionar una categoria por id recibiendo el nombre
     * <p>
     * select c.name , c.status
     * from category c
     * where c.name like '%puertas%' and c.status = 'ACTIVE');
     */

    @Query(value = "select c " +
            "from Category c " +
            "where c.name like %:categoryName% and c.status = :status")
    List<Category> findAllCategoriesByName(@Param("categoryName") String categoryName,
                                           @Param("status") RegisterStatusEnum status);

    @Query(value = "select c " +
            "from Category c " +
            "where c.status = :status")
    List<Category> findAllActives(@Param("status") RegisterStatusEnum status);


}
