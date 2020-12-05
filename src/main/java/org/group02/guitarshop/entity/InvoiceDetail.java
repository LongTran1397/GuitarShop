package org.group02.guitarshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "InvoiceDetail")
public class InvoiceDetail implements Serializable {
    @EmbeddedId
    private InvoiceDetailIdentity invoiceDetailIdentity;

    @Column(name = "quantity", nullable = true)
    private Integer quantity;

    public InvoiceDetail() { }

    public InvoiceDetail(InvoiceDetailIdentity invoiceDetailIdentity, Integer quantity) {
        this.invoiceDetailIdentity = invoiceDetailIdentity;
        this.quantity = quantity;
    }

    public InvoiceDetail(int idInvoice, int idProduct, Integer quantity) {
        InvoiceDetailIdentity invoiceDetailIdentity = new InvoiceDetailIdentity(idInvoice, idProduct);
        this.invoiceDetailIdentity = invoiceDetailIdentity;
        this.quantity = quantity;
    }

    @MapsId("idInvoice")
    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Invoice invoiceByIdInvoice;

    @MapsId("idProduct")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product productByIdProduct;
}
