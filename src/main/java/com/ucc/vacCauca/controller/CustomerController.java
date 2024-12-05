package com.ucc.vacCauca.controller;

import com.ucc.vacCauca.common.GeneralBodyResponse;
import com.ucc.vacCauca.config.security.AuthenticatedUser;
import com.ucc.vacCauca.domain.dto.CustomerDTO;
import com.ucc.vacCauca.domain.entity.Customer;
import com.ucc.vacCauca.domain.payload.CustomerForm;
import com.ucc.vacCauca.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("saveCustomer")
    public ResponseEntity<GeneralBodyResponse<CustomerDTO>> saveCustomer(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                         @RequestBody CustomerForm customerForm) {
        try {
            CustomerDTO customer = this.customerService.save(customerForm);
            if (customer.getId() != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(customer, "create", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateCustomer/{idCustomer}")
    public ResponseEntity<GeneralBodyResponse<CustomerDTO>> updateCustomer(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                           @PathVariable("idCustomer") Long idCustomer,
                                                                           @RequestBody CustomerForm customerForm) {
        try {
            CustomerDTO customer = this.customerService.update(customerForm, idCustomer);

            if (customer != null) {
                return new ResponseEntity<>(new GeneralBodyResponse<>(customer, "update", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllCustomers")
    public ResponseEntity<GeneralBodyResponse<List<Customer>>> getAllCustomers() {
        try {
            List<Customer> customers = this.customerService.getAllCustomers();
            if (!customers.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(customers, "get all", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("searchCustomerByIdentification")
    public ResponseEntity<GeneralBodyResponse<Optional<Customer>>> getCustomerByIdentificationNumber
            (@RequestBody CustomerForm customerForm) {
        try {
            Optional<Customer> customer = this.customerService.getCustomerByIdentificationNumber(customerForm.getIdentification());

            if (customer.isPresent()) {
                return new ResponseEntity<>(new GeneralBodyResponse<>(customer, "search customer by identification", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail - not found", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteCustomer/{idCustomer}")
    public ResponseEntity<GeneralBodyResponse> deleteCustomer(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                              @PathVariable("idCustomer") Long idCustomer) {
        try {
            if (idCustomer != null && idCustomer != 0) {
                this.customerService.delete(idCustomer);
                return new ResponseEntity<>(new GeneralBodyResponse<>(true, "deleted", null), (HttpStatus.OK));
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "no exist", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
