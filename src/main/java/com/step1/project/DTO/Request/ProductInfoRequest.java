package com.step1.project.DTO.Request;

import lombok.*;

@Data
public class ProductInfoRequest {

    public String productCode;

    public String oldProductCode;

    public int categoryCode;

    public int priceId;

    public int stockId;

    public String productDescription;

    public String oldProductDescription;

    public String categoryName;

    public String oldCategoryName;

    public int price;

    public int oldPrice;

    public String currency;

    public String oldCurrency;

    public int inventoryAvailable;

    public int oldInventoryAvailable;

    public String location;

    public String oldLocation;


}
