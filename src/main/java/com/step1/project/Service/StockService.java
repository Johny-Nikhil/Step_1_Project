package com.step1.project.Service;

import com.step1.project.Entity.Stock;
import com.step1.project.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public void delete(Stock stock) {
        stockRepository.delete(stock);
    }
}
