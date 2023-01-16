package com.step1.project.Service;

import com.step1.project.DTO.Request.ProductInfoRequest;
import com.step1.project.Entity.Category;
import com.step1.project.Entity.Price;
import com.step1.project.Entity.Product;
import com.step1.project.Entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CrudService {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PriceService priceService;

    @Autowired
    StockService stockService;

    public boolean addProduct(ProductInfoRequest request) throws NoSuchElementException {
        Optional<Product> existingProduct = productService.getProductById(request.getProductCode());
        if(existingProduct.isEmpty()){
            Product product = new Product();
            product.setProductCode(request.getProductCode());
            product.setProductDescription(request.getProductDescription());

            Category existingCategory = categoryService.getCategoryByName(request.getCategoryName());

            if(existingCategory == null){
                Category category = new Category();
                category.setCategoryName(request.getCategoryName());
                category = categoryService.save(category);
                product.setCategoryCode(category.getCategoryCode());
                product.setCategory(category);
            }else{
                product.setCategoryCode(existingCategory.getCategoryCode());
                product.setCategory(existingCategory);
            }
            Price price = new Price();
            price.setPrice(request.getPrice());
            price.setCurrency(request.getCurrency());
            price.setProductCode(product.getProductCode());
            priceService.save(price);

            product.setPriceList(new ArrayList<Price>(Arrays.asList(price)));

            Stock stock = new Stock();
            stock.setProductCode(product.getProductCode());
            stock.setLocation(request.getLocation());
            stock.setInventoryAvailable(request.getInventoryAvailable());

            product.setStockList(new ArrayList<>(Arrays.asList(stock)));

            productService.save(product);

        }else{
            Category existingCategory = categoryService.getCategoryByName(request.getCategoryName());
            Product product = existingProduct.get();
            if(existingCategory == null){
                Category category = new Category();
                category.setCategoryName(request.getCategoryName());
                categoryService.save(category);
                product.setCategory(category);
                product.setCategoryCode(category.getCategoryCode());
            }else{
                product.setCategoryCode(existingCategory.getCategoryCode());
                product.setCategory(existingCategory);
            }
            boolean exists = false;
            for(Price p : product.getPriceList()){
                if(p.getCurrency().equals(request.getCurrency()) && p.getPrice() == request.getPrice()){
                    exists = true;
                    break;
                }
            }

            if(!exists){
                Price price = new Price();
                price.setProductCode(product.getProductCode());
                price.setCurrency(request.getCurrency());
                price.setPrice(request.getPrice());
                priceService.save(price);
                product.getPriceList().add(price);
                product.setPriceList(product.getPriceList());
            }

            exists = false;

            for(Stock s : product.getStockList()){
                if(request.getInventoryAvailable() == s.getInventoryAvailable() && request.getLocation().equals(s.getLocation())){
                    exists = true;
                    break;
                }
            }
            if(!exists){
                Stock stock = new Stock();
                stock.setInventoryAvailable(request.getInventoryAvailable());
                stock.setProductCode(product.getProductCode());
                stock.setLocation(request.getLocation());
                stock = stockService.save(stock);
                product.getStockList().add(stock);
            }
            productService.save(product);
        }
        return true;
    }
    public boolean deleteProduct(ProductInfoRequest request) {
        log.info(request.toString());
        Optional<Product> existingProduct = productService.getProductById(request.getProductCode());
        log.info(existingProduct.toString());
        if(!existingProduct.isEmpty()){
            Product product = existingProduct.get();
            for(Price price : product.getPriceList()){
                if(price.getPrice() == request.getPrice() && price.getCurrency().equals(request.getCurrency())){
                    if(product.getPriceList().size() > 1){
                        log.info(price.toString());
                        productService.delete(product);
                        product.setPriceList(product.getPriceList()
                                .stream()
                                .filter(x -> x.getId() != price.getId())
                                .collect(Collectors.toList()));
                    }else{
                        productService.delete(product);
                        return true;
                    }
                }
            }

            for(Stock stock : product.getStockList()){
                if(stock.getLocation().equals(request.getLocation()) && stock.getInventoryAvailable() == request.getInventoryAvailable()){
                    if(product.getStockList().size() > 1){
                        log.info(stock.toString());
                        productService.delete(product);
                        product.setStockList(product.getStockList()
                                .stream()
                                .filter(x -> x.getId() != stock.getId())
                                .collect(Collectors.toList()));
                    }else{
                        productService.delete(product);
                        return true;
                    }
                }
            }

            productService.save(product);
            return true;

        }else{

            return false;

        }
    }

    public boolean updateProduct(ProductInfoRequest request){
        Optional<Product> existingProduct = productService.getProductById(request.getProductCode());
        if(existingProduct.isEmpty()){
            addProduct(request);
            ProductInfoRequest oldRequest = new ProductInfoRequest();
            oldRequest.setProductCode(request.getOldProductCode());
            oldRequest.setCategoryName(request.getOldCategoryName());
            oldRequest.setProductDescription(request.getOldProductDescription());
            oldRequest.setPrice(request.getOldPrice());
            oldRequest.setCurrency(request.getOldCurrency());
            oldRequest.setInventoryAvailable(request.getInventoryAvailable());
            oldRequest.setLocation(request.getOldLocation());
            deleteProduct(oldRequest);
        }else{
            Product product = existingProduct.get();
            if(!product.getProductCode().equals(request.getOldProductCode())){
                ProductInfoRequest oldRequest = new ProductInfoRequest();
                oldRequest.setProductCode(request.getOldProductCode());
                oldRequest.setCategoryName(request.getOldCategoryName());
                oldRequest.setProductDescription(request.getOldProductDescription());
                oldRequest.setPrice(request.getOldPrice());
                oldRequest.setCurrency(request.getOldCurrency());
                oldRequest.setInventoryAvailable(request.getInventoryAvailable());
                oldRequest.setLocation(request.getOldLocation());
                deleteProduct(oldRequest);
                addProduct(request);
            }else{
                product.setProductDescription(request.getProductDescription());

                Category existingCategory = categoryService.getCategoryByName(request.getCategoryName());
                if(existingCategory == null){
                    Category category = new Category();
                    category.setCategoryName(request.getCategoryName());
                    categoryService.save(category);
                    product.setCategory(category);
                    product.setCategoryCode(category.getCategoryCode());
                }else{
                    product.setCategoryCode(existingCategory.getCategoryCode());
                    product.setCategory(existingCategory);
                }
                boolean exists = false;
                Price oldPrice = null;
                for(Price p : product.getPriceList()){
                    if(p.getCurrency().equals(request.getOldCurrency()) && p.getPrice() == request.getOldPrice()){
                        oldPrice = p;
                    }
                    if(p.getCurrency().equals(request.getCurrency()) && p.getPrice() == request.getPrice()){
                        exists = true;
                    }
                }

                if(!exists){
                    Price price = new Price();
                    price.setProductCode(product.getProductCode());
                    price.setCurrency(request.getCurrency());
                    price.setPrice(request.getPrice());
                    priceService.save(price);
                    product.getPriceList().add(price);
                    product.setPriceList(product.getPriceList());
                }
                product.getPriceList().remove(oldPrice);

                exists = false;
                Stock oldStock = null;
                for(Stock s : product.getStockList()){
                    if(request.getOldInventoryAvailable() == s.getInventoryAvailable() && request.getOldLocation().equals(s.getLocation())){
                        oldStock = s;
                    }
                    if(request.getInventoryAvailable() == s.getInventoryAvailable() && request.getLocation().equals(s.getLocation())){
                        exists = true;
                    }
                }
                if(!exists){
                    Stock stock = new Stock();
                    stock.setInventoryAvailable(request.getInventoryAvailable());
                    stock.setProductCode(product.getProductCode());
                    stock.setLocation(request.getLocation());
                    stock = stockService.save(stock);
                    product.getStockList().add(stock);
                }
                product.getStockList().remove(oldStock);
                productService.delete(product);
                productService.save(product);
            }
        }
        return true;
    }

}
