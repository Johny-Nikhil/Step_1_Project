package com.step1.project.Controller;

import com.step1.project.DTO.ProductInfo;
import com.step1.project.DTO.Request.ProductInfoRequest;
import com.step1.project.Entity.Category;
import com.step1.project.Entity.Price;
import com.step1.project.Entity.Product;
import com.step1.project.Entity.Stock;
import com.step1.project.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
public class RequestController {

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StockService stockService;

    @Autowired
    CrudService crudService;


    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public List<ProductInfo> getSortedProducts(@RequestParam("fieldName") String fieldName, @RequestParam("sortDirection") String sortDirection, @RequestParam("currentPage") Integer currentPage){
        log.info(fieldName + " " +sortDirection + " _ " + currentPage.toString());
        return productService.getSortedProducts(fieldName, sortDirection, currentPage);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long getTotalCount(){
        long pageCount = productService.getTotalCount();
        return pageCount % 10 > 0 ? pageCount / 10 + 1 : pageCount / 10;
    }

    @PostMapping(value = "/add/product", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean add(ProductInfoRequest request){
        return crudService.addProduct(request);
    }



    @PostMapping(value = "/delete/product", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean delete(ProductInfoRequest request){
        return crudService.deleteProduct(request);
    }

    @PostMapping(value = "/update/product", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean update(ProductInfoRequest request){
        return crudService.updateProduct(request);
    }


}
