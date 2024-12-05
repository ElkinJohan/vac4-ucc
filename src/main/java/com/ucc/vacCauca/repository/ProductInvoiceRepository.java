package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.ProductInvoice;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInvoiceRepository extends JpaRepository<ProductInvoice, Long> {

    //buscar item_invoice por numero de invoice
    @Query(value = "select ii, i, p " +
            "from ProductInvoice ii " +
            "inner join Invoice i " +
            "on(ii.idInvoice = i.id) " +
            "inner join Product p " +
            "on(p.id = ii.idProduct)" +
            "and i.numberInvoice = :numberInvoice and ii.status = :status")
    List<ProductInvoice> findAllInvoicesByNumberInvoice(@Param("numberInvoice") Long numberInvoice,
                                                        @Param("status") RegisterStatusEnum status);

    //traer all invoices activas
    @Query(value = "select ii, i, p " +
            "from ProductInvoice ii " +
            "inner join Invoice i " +
            "on(ii.idInvoice = i.id) " +
            "inner join Product p " +
            "on(p.id = ii.idProduct)" +
            "and ii.status = :status")
    List<ProductInvoice> findAllActives(@Param("status") RegisterStatusEnum status);

    //traer all de invoice con relacion
    /**
     * select * from invoice i inner join product_invoice pi on(i.id = pi.id_invoice)
     * inner join product p on(p.id = pi.id_product) inner join customer c on(c.id = i.id_customer);
     */
    @Query(value = "select i, pi, c from Invoice i inner join ProductInvoice pi on(i.id = pi.idInvoice) " +
            "inner join Product p on(p.id = pi.idProduct) inner join Customer c on(c.id = i.idCustomer)")
    List<Object[]> findAllInvoicesAboutAll();
}
