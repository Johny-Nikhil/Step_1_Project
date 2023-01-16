package com.step1.project.Service;

import com.step1.project.DTO.ProductInfo;
import com.step1.project.Entity.Product;
import com.step1.project.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    private int limit = 10;


    public List<ProductInfo> getSortedProducts(String fieldName, String sortDirection, Integer currentPage){
        Pageable page = sortDirection.equals("DESC") ? PageRequest.of(currentPage, limit, Sort.by(fieldName).descending()) : PageRequest.of(currentPage, limit, Sort.by(fieldName).ascending());
        return productRepository.getSortedProducts(page);
    }

    public long getTotalCount() {
        return productRepository.getTotalCount();
    }

    public List<Product> findAllProducts(){
         List<Product> result = new ArrayList<>();
         productRepository.findAll(PageRequest.of(0,limit,Sort.by("priceList").descending())).forEach(result::add);
//         log.info(result.get(0).getProductCode());
         return result;
    }

    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }


    public void delete(Product product){
        productRepository.delete(product);
    }

}
