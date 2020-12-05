package org.group02.guitarshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ProductImage")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Lob
    @Column(name = "data", nullable = true)
    private String data;

    @Column(name = "product_id", nullable = true)
    private Integer idProduct;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product productByIdProduct;

 }
