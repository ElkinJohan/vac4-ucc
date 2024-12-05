package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {

    /**
     * encontrar por id del producto para setear el status a INACTIVE
     * select *
     * from generate_product gp
     * inner join product p
     * on(gp.id_product = p.id)
     * and p.id = 2;
     */
    @Query(value = "select gp, p " +
            "from ProductMaterial gp " +
            "inner join Product p " +
            "on(gp.idProduct = p.id) " +
            "and p.id = :idProduct")
    List<ProductMaterial> findAllByIdProductToChangeValue(@Param("idProduct") Long idProduct);

    /**
     * borrar por material y producto
     * <p>
     * DELETE FROM projectvac.product_material
     * WHERE id_material = 2 and id_product = 1;
     */
    @Transactional
    @Modifying
    @Query(value = "delete from ProductMaterial " +
            "where idMaterial = :idMaterial and idProduct = :idProduct")
    void deleteByIdMaterialIdProduct(@Param("idMaterial") Long idMaterial,
                                     @Param("idProduct") Long idProduct);

}
