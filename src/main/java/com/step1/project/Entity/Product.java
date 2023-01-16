package com.step1.project.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Product implements Serializable {

    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_description", unique = false)
    private String productDescription;

    @Column(name = "category_code")
    private int categoryCode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="category_code" , referencedColumnName = "category_code", insertable = false, updatable = false)
    private Category category;


    @OneToMany(targetEntity = Price.class,cascade = CascadeType.ALL,mappedBy="productCode")
    private List<Price> priceList;

    @OneToMany(targetEntity = Stock.class,cascade = CascadeType.ALL,mappedBy="productCode")
    private List<Stock> stockList;
}
