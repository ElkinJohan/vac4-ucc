package com.ucc.vacCauca.service.impl;

import com.ucc.vacCauca.domain.dto.CustomerDTO;
import com.ucc.vacCauca.domain.dto.InvoiceDTO;
import com.ucc.vacCauca.domain.entity.*;
import com.ucc.vacCauca.domain.payload.InvoiceForm;
import com.ucc.vacCauca.domain.payload.ProductForm;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import com.ucc.vacCauca.repository.*;
import com.ucc.vacCauca.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInvoiceRepository productInvoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Invoice> getAllInvoices() {

        return this.invoiceRepository.findAllActives(RegisterStatusEnum.ACTIVE);
    }

    @Override
    public List<Invoice> getAllInvoicesByIdentificationNumber(Long identification) {
        return this.invoiceRepository.findAllInvoicesByIdentificationNumber(identification, RegisterStatusEnum.ACTIVE);
    }

    @Override
    public InvoiceDTO save(InvoiceForm form) {

        //validar si existen los productos
        List<Product> productListExist = new ArrayList<>();
        for (ProductForm productForm :
                form.getProductList()) {
            productListExist.add(this.productRepository.findById(productForm.getId()).get());
        }

        Invoice invoice = new Invoice();

        if (form.getId() != null) {
            invoice.setId(form.getId());
        }

        invoice.setStatus(RegisterStatusEnum.ACTIVE);
        invoice.setDate(this.getDateString());
        invoice.setNumberInvoice(this.toNewNumberInvoice());
        invoice.setProgressStatus(form.getProgressStatus());
        invoice.setIdCustomer(form.getIdCustomer());
        invoice.setIdUser(form.getIdUser());

        invoice = this.invoiceRepository.save(invoice);

        for (ProductForm productListForm : form.getProductList()) {

            if (productListForm.getAction() == 1) {
                ProductInvoice productInvoice = new ProductInvoice();

                productInvoice.setStatus(RegisterStatusEnum.ACTIVE);
                productInvoice.setIdInvoice(invoice.getId());
                productInvoice.setIdProduct(productListForm.getId());
                productInvoice.setSizeX(productListForm.getSizeX());
                productInvoice.setSizeY(productListForm.getSizeY());
                productInvoice.setQuantity(productListForm.getQuantity());
                productInvoice.setTotalSizeM2(this.toTotalSizeM2(productListForm));
                productInvoice.setTotalPriceWithSize(this.toTotalPriceInvoice(productListForm));

                productInvoiceRepository.save(productInvoice);
            }
        }

        InvoiceDTO responseDTO = new InvoiceDTO();

        responseDTO.setId(invoice.getId());
        responseDTO.setStatus(invoice.getStatus());
        responseDTO.setDate(invoice.getDate());
        responseDTO.setNumberInvoice(invoice.getNumberInvoice());
        responseDTO.setProgressStatus(invoice.getProgressStatus());

        Optional<Customer> optionalCustomer = customerRepository.findById(form.getIdCustomer());
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(optionalCustomer.get().getId());
        customerDTO.setEmail(optionalCustomer.get().getEmail());
        customerDTO.setStatus(optionalCustomer.get().getStatus());
        customerDTO.setFullName(optionalCustomer.get().getFullName());
        customerDTO.setIdentification(optionalCustomer.get().getIdentification());
        customerDTO.setHomeAddress(optionalCustomer.get().getHomeAddress());
        customerDTO.setPhone(optionalCustomer.get().getPhone());

        Optional<User> optionalUser = userRepository.findById(form.getIdUser());
        User userDTO = new User();
        userDTO.setId(optionalUser.get().getId());
        userDTO.setStatus(optionalUser.get().getStatus());
        userDTO.setIdentification(optionalUser.get().getIdentification());
        userDTO.setFullName(optionalUser.get().getFullName());
        userDTO.setEmail(optionalUser.get().getEmail());
        userDTO.setPhone(optionalUser.get().getPhone());
        userDTO.setUserRolEnum(optionalUser.get().getUserRolEnum());
        userDTO.setPassword(optionalUser.get().getPassword());

        responseDTO.setCustomer(customerDTO);
        responseDTO.setUserDTO(userDTO);
        return responseDTO;
    }

    @Override
    public InvoiceDTO update(InvoiceForm form, Long id) {

        if (invoiceRepository.existsById(id)) {
            return this.save(form);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {

        Optional<Invoice> optionalInvoice = this.invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setStatus(RegisterStatusEnum.INACTIVE);
            Invoice save = this.invoiceRepository.save(invoice);
            return save.getId() != null;
        } else {
            return false;
        }
    }

    //obtener los metros cuadrados totales
    private BigDecimal toTotalSizeM2(ProductForm form) {
        return form.getSizeX().multiply(form.getSizeY());
    }

    //obtener el valor total m2 * cantidad * valor unitario
    private BigDecimal toTotalPriceInvoice(ProductForm form) {
        BigDecimal totalSizeM2 = toTotalSizeM2(form);
        BigDecimal quantityAndM2 = form.getQuantity().multiply(totalSizeM2);

        Long totalPriceById = this.productRepository.findTotalPriceById(form.getId());

        return quantityAndM2.multiply(BigDecimal.valueOf(totalPriceById));
    }

    //obtener fecha actual
    private String getDateString() {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(formatter.format(date));

        return formatter.format(date);
    }

    //obtener el número de invoice automáticamente
    private Long toNewNumberInvoice() {

        Long maxNumberInvoice = invoiceRepository.maxNumberInvoice();
        long newNumberInvoice = 0;

        if (maxNumberInvoice == null) {
            newNumberInvoice = 100;
        } else {
            newNumberInvoice = maxNumberInvoice + 1;
        }

        return newNumberInvoice;
    }
}
