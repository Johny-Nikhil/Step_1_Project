package com.step1.project.Repository;

import com.step1.project.DTO.ProductInfo;
import com.step1.project.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {


    @Query(value = "SELECT " +
            "product.product_code as productCode, " +
            "product.product_description as productDescription, " +
            "category.category_name as categoryName, " +
            "price.price_id as priceId, " +
            "price.price as price, " +
            "price.currency as currency, " +
            "stock.stock_id as stockId, " +
            "stock.inventory_available as inventoryAvailable, " +
            "stock.location as location " +
            "from product " +
            "JOIN category ON " +
            "category.category_code = product.category_code " +
            "JOIN price ON " +
            "price.product_code = product.product_code " +
            "JOIN stock ON " +
            "stock.product_code = product.product_code ", nativeQuery = true)
    List<ProductInfo> getSortedProducts(Pageable page);




    @Query(value = "SELECT " +
            "count(*) " +
            "from product " +
            "JOIN category ON " +
            "category.category_code = product.category_code " +
            "JOIN price ON " +
            "price.product_code = product.product_code " +
            "JOIN stock ON " +
            "stock.product_code = product.product_code ", nativeQuery = true)
    long getTotalCount();


}
