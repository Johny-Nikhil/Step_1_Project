package com.step1.project.DTO;


// Interface projection
public interface ProductInfo {
    String getProductCode();
    String getProductDescription();
    String getCategoryName();

    Integer getPriceId();

    Integer getPrice();
    String getCurrency();

    Integer getStockId();

    Integer getInventoryAvailable();
    String getLocation();

}
