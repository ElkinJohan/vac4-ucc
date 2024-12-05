package com.ucc.vacCauca.controller;

import com.ucc.vacCauca.common.GeneralBodyResponse;
import com.ucc.vacCauca.config.security.AuthenticatedUser;
import com.ucc.vacCauca.domain.dto.InvoiceDTO;
import com.ucc.vacCauca.domain.entity.Invoice;
import com.ucc.vacCauca.domain.entity.ProductInvoice;
import com.ucc.vacCauca.domain.payload.CustomerForm;
import com.ucc.vacCauca.domain.payload.InvoiceForm;
import com.ucc.vacCauca.service.InvoiceService;
import com.ucc.vacCauca.service.ProductInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductInvoiceService productInvoiceService;

    @PostMapping("saveInvoice")
    public ResponseEntity<GeneralBodyResponse<InvoiceDTO>> saveInvoice(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                       @RequestBody InvoiceForm invoiceForm) {
        try {
            InvoiceDTO invoice = this.invoiceService.save(invoiceForm);
            if (invoice.getId() != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(invoice, "create", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateInvoice/{idInvoice}")
    public ResponseEntity<GeneralBodyResponse<InvoiceDTO>> updateInvoice(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                                         @PathVariable("idInvoice") Long idInvoice,
                                                                         @RequestBody InvoiceForm invoiceForm) {
        try {
            InvoiceDTO invoice = this.invoiceService.update(invoiceForm, idInvoice);

            if (invoice != null)
                return new ResponseEntity<>(new GeneralBodyResponse<>(invoice, "update", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "fail", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("getAllInvoices")
    public ResponseEntity<GeneralBodyResponse<List<Invoice>>> getAllInvoices() {
        try {
            List<Invoice> invoices = this.invoiceService.getAllInvoices();

            if (!invoices.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(invoices, "get all", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("searchInvoicesByIdentification")
    public ResponseEntity<GeneralBodyResponse<List<Invoice>>> searchInvoicesByIdentificationNumber(
            @RequestBody CustomerForm customerForm) {
        try {
            List<Invoice> invoices = this.invoiceService.getAllInvoicesByIdentificationNumber(customerForm.getIdentification());
            if (!invoices.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(invoices, "search invoices by identification", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteInvoice/{idInvoice}")
    public ResponseEntity<GeneralBodyResponse> deleteInvoice(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                             @PathVariable("idInvoice") Long idInvoice) {
        try {
            if (idInvoice != null && idInvoice != 0) {
                this.invoiceService.delete(idInvoice);
                return new ResponseEntity<>(new GeneralBodyResponse<>(true, "deleted", null), (HttpStatus.OK));
            } else {
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "no exist", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("seachrAllInvoicesAboutAll")public ResponseEntity<GeneralBodyResponse<List<Object[]>>> getAllInvoicesAboutAll() {
        try {
            List<Object[]> aboutAll = this.productInvoiceService.getAllInvoicesAboutAll();

            if (!aboutAll.isEmpty())
                return new ResponseEntity<>(new GeneralBodyResponse<>(aboutAll, "get all about all invoices", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new GeneralBodyResponse<>(null, "empty", null), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(new GeneralBodyResponse<>(null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
