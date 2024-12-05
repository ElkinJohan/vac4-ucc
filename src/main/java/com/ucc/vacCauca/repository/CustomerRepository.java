package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.Customer;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Encontrar Customer por id con identificacion
     *
     * select c2.status, c2.email, c2.full_name, c2.home_address, c2.identification, c2.phone
     *      from customer c2
     *      where c2.id = (select c3.id
     *      				from customer c3
     *      				where c3.identification = 51651298);
     * **/

    @Query(value = "select c " +
            " from Customer c " +
            "where c.identification = :identification and c.status = :status")
    Optional<Customer> findCustomerByIdentificationNumber(@Param("identification") Long identification,
                                                          @Param("status") RegisterStatusEnum statusEnum);

    @Query(value = "select c " +
            " from Customer c " +
            "where c.status = :status")
    List<Customer> findAllActives(@Param("status") RegisterStatusEnum statusEnum);



}
