package com.step1.project.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private int id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "location")
    private String location;

    @Column(name = "inventory_available")
    private long inventoryAvailable;
}
