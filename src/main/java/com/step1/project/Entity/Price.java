package com.step1.project.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Price{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Integer id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "price")
    private int price;


    @Column(name = "currency")
    private String currency;


}
