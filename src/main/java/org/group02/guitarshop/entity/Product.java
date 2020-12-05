package org.group02.guitarshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "price", nullable = true)
    private Double price;

    @Column(name = "discount_amount", nullable = true)
    private Integer discountAmount;

    @Column(name = "average_rate", nullable = true, precision = 0)
    private Double averageRate;

    @Lob
    @Column(name = "main_img", nullable = true)
    private String imageThumbnail;

    @Column(name = "quantity", nullable = true)
    private Integer quantity;


    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "style", nullable = true)
    private String style;

    @Column(name = "warranty", nullable = true)
    private String warrantyPeriod;


    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;

    @Column(name = "category_id", nullable = true)
    private Integer idCategory;

    @Column(name = "manufacturer_id", nullable = true)
    private Integer idManufacturer;

    @Column(name = "metadata", nullable = true)
    private String metadata;

    @OneToMany(mappedBy = "productByIdProduct")
    private Collection<InvoiceDetail> invoiceDetailsById;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category categoryByIdCategory;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Manufacturer manufacturerByIdManufacturer;

    @OneToMany(mappedBy = "productByIdProduct")
    private Collection<ProductImage> productImagesById;

    @OneToMany(mappedBy = "productByIdProduct")
    private Collection<Rate> ratesById;
}
