package com.ucc.vacCauca.domain.dto;

public class InvoiceAllDTO {

    private InvoiceDTO invoiceDTO;
    private CustomerDTO customerDTO;
    private ProductDTO productDTO;
    private ProductInvoiceDTO productInvoiceDTO;

    public InvoiceDTO getInvoiceDTO() {
        return invoiceDTO;
    }

    public void setInvoiceDTO(InvoiceDTO invoiceDTO) {
        this.invoiceDTO = invoiceDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ProductInvoiceDTO getProductInvoiceDTO() {
        return productInvoiceDTO;
    }

    public void setProductInvoiceDTO(ProductInvoiceDTO productInvoiceDTO) {
        this.productInvoiceDTO = productInvoiceDTO;
    }
}
