package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.Invoice;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    //buscar invoices por numero de identificacion del usuario
    @Query(value = "select i, c " +
            "from Invoice i " +
            "inner join Customer c " +
            "on(i.customer.id = c.id) " +
            "where c.identification = :identification and i.status = :status")
    List<Invoice> findAllInvoicesByIdentificationNumber(@Param("identification") Long identification,
                                                        @Param("status") RegisterStatusEnum status);

    //traer all invoices activas
    @Query(value = "select i, c " +
            "from Invoice i " +
            "inner join Customer c " +
            "on(i.user.id = c.id) " +
            "where i.status = :status")
    List<Invoice> findAllActives(@Param("status") RegisterStatusEnum status);

    //obtener el mayor número de factura
    @Query(value = "select max(i.numberInvoice)" +
            "from Invoice i")
    Long maxNumberInvoice();

}
