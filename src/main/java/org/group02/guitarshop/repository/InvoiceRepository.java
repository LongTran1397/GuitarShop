package org.group02.guitarshop.repository;

import org.group02.guitarshop.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
