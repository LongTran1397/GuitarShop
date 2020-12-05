package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.Invoice;

public interface InvoiceService {
    Integer insertInvoice(Invoice invoice);
    Invoice getInvoiceById(Integer id);
}
