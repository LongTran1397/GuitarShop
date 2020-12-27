package org.group02.guitarshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "star", nullable = true, precision = 0)
    private int numberOfStars;

    @Column(name = "product_id", nullable = true)
    private Integer idProduct;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product productByIdProduct;

}
