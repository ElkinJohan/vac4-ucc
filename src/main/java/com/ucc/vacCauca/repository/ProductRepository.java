package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.Product;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //todos los products por nombre like
    @Query(value = "select p " +
            "from Product p " +
            "where p.name like %:productName% and p.status = :status")
    List<Product> findAllProductsByNameLike(@Param("productName") String productName,
                                             @Param("status") RegisterStatusEnum status);

    //todos losproducts activos
    @Query(value = "select p " +
            "from Product p " +
            "where p.status = :status")
    List<Product> findAllActives(@Param("status") RegisterStatusEnum status);

    //traer precio del producto por id
    @Query(value = "select p.totalPrice " +
            "from Product p " +
            "where p.id = :idProduct")
    Long findTotalPriceById(@Param("idProduct") Long idProduct);

}