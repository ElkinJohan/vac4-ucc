package com.ucc.vacCauca.service;

import com.ucc.vacCauca.domain.dto.InvoiceDTO;
import com.ucc.vacCauca.domain.entity.Invoice;
import com.ucc.vacCauca.domain.payload.InvoiceForm;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getAllInvoices();

    List<Invoice> getAllInvoicesByIdentificationNumber(Long identification);

    InvoiceDTO save(InvoiceForm form);

    InvoiceDTO update(InvoiceForm form, Long id);

    boolean delete(Long id);
}
