package com.ucc.vacCauca.service.impl;

import com.ucc.vacCauca.domain.dto.CustomerDTO;
import com.ucc.vacCauca.domain.entity.Customer;
import com.ucc.vacCauca.domain.payload.CustomerForm;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import com.ucc.vacCauca.repository.CustomerRepository;
import com.ucc.vacCauca.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServicelmpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO save(CustomerForm form) {
        Customer customer = new Customer();

        if(form.getId() != null){
            customer.setId(form.getId());
        }

        customer.setStatus(RegisterStatusEnum.ACTIVE);
        customer.setEmail(form.getEmail());
        customer.setFullName(form.getFullName());
        customer.setHomeAddress(form.getHomeAddress());
        customer.setIdentification(form.getIdentification());
        customer.setPhone(form.getPhone());

        customer = this.customerRepository.save(customer);

        CustomerDTO responseDTO = new CustomerDTO();

        responseDTO.setId(customer.getId());
        responseDTO.setStatus(customer.getStatus());
        responseDTO.setEmail(customer.getEmail());
        responseDTO.setFullName(customer.getFullName());
        responseDTO.setHomeAddress(customer.getHomeAddress());
        responseDTO.setIdentification(customer.getIdentification());
        responseDTO.setPhone(customer.getPhone());

        return responseDTO;

    }

    @Override
    public CustomerDTO update(CustomerForm form, Long id) {

        if (customerRepository.existsById(id)) {
            return this.save(form);
        } else {
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAllActives(RegisterStatusEnum.ACTIVE);
    }

    @Override
    public Optional<Customer> getCustomerByIdentificationNumber(Long identification) {
        return this.customerRepository.findCustomerByIdentificationNumber(identification, RegisterStatusEnum.ACTIVE);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setStatus(RegisterStatusEnum.INACTIVE);
            Customer save = this.customerRepository.save(customer);
            return save.getId() != null;
        } else {
            return false;
        }
    }


}
