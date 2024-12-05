package com.ucc.vacCauca.service;

import com.ucc.vacCauca.domain.dto.CustomerDTO;

import com.ucc.vacCauca.domain.entity.Customer;
import com.ucc.vacCauca.domain.payload.CustomerForm;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerByIdentificationNumber(Long identification);

    CustomerDTO save(CustomerForm form);

    CustomerDTO update(CustomerForm form, Long id);

    boolean delete(Long id);
}
