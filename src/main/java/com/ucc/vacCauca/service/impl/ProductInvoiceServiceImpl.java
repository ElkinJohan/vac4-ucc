package com.ucc.vacCauca.service.impl;

import com.ucc.vacCauca.domain.dto.InvoiceAllDTO;
import com.ucc.vacCauca.domain.entity.ProductInvoice;
import com.ucc.vacCauca.repository.ProductInvoiceRepository;
import com.ucc.vacCauca.service.ProductInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductInvoiceServiceImpl implements ProductInvoiceService {

    @Autowired
    private ProductInvoiceRepository productInvoiceRepository;

    @Override
    public List<Object[]> getAllInvoicesAboutAll() {


        return this.productInvoiceRepository.findAllInvoicesAboutAll();
    }
}
