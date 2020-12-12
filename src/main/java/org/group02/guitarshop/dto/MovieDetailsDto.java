package org.group02.guitarshop.dto;

import lombok.Data;
import org.group02.guitarshop.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
class ProductDto {
    private String name;

    private Double price;

    private Integer discountAmount;

    private Double averageRate;

    private String imageThumbnail;

    private Integer quantity;

    private String description;

    private String style;

    private String warrantyPeriod;

    private Timestamp createdTime;

    private Integer idCategory;

    private Integer idManufacturer;

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
