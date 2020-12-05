package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.Invoice;
import org.group02.guitarshop.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository repository;

    @Override
    public Integer insertInvoice(Invoice invoice) {
        Invoice returnedInvoice = repository.save(invoice);
        return returnedInvoice.getId();
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        Invoice invoice = repository.findById(id).get();
        return invoice;
    }
}
