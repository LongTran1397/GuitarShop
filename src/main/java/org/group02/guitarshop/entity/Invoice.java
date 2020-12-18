package org.group02.guitarshop.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_message")
    private String customerMessage;

    @Column(name = "total")
    private Double total;

    @Column(name = "payment_method")
    private String paymentMethod;

    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "person_id")
    private Integer idPerson;

    @Column(name = "discount_id")
    private Integer idDiscountCode;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DiscountCode discountCodeByIdDiscountCode;

    @OneToMany(mappedBy = "invoiceByIdInvoice")
    private Collection<InvoiceDetail> invoiceDetailsById;
}
