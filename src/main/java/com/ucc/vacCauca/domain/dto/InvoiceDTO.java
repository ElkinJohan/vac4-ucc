package com.ucc.vacCauca.domain.dto;

import com.ucc.vacCauca.domain.entity.AbstractEntity;
import com.ucc.vacCauca.domain.entity.User;
import com.ucc.vacCauca.enums.ProgressStatus;

import java.util.List;

public class InvoiceDTO extends AbstractEntity {

    private Long numberInvoice;
    private String date;
    private ProgressStatus progressStatus;
    private CustomerDTO Customer;
    private User UserDTO;
    private List<ProductDTO> productDTOList;

    public CustomerDTO getCustomer() {
        return Customer;
    }

    public void setCustomer(CustomerDTO customer) {
        Customer = customer;
    }

    public User getUserDTO() {
        return UserDTO;
    }

    public void setUserDTO(User userDTO) {
        UserDTO = userDTO;
    }

    public Long getNumberInvoice() {
        return numberInvoice;
    }

    public void setNumberInvoice(Long numberInvoice) {
        this.numberInvoice = numberInvoice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }
}
