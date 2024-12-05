package com.ucc.vacCauca.service;

import com.ucc.vacCauca.domain.dto.InvoiceAllDTO;
import com.ucc.vacCauca.domain.entity.ProductInvoice;

import java.util.List;

public interface ProductInvoiceService {

    List<Object[]> getAllInvoicesAboutAll();
}
