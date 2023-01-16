package com.step1.project.Service;

import com.step1.project.Entity.Price;
import com.step1.project.Repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PriceService {

    @Autowired
    PriceRepository priceRepository;
    public List<Price> sortedPrices(){
        List<Price> result = new ArrayList<>();
        priceRepository.findAll(PageRequest.of(1,10,Sort.by("price").descending())).forEach(result::add);
        return result;
    }

    public Price save(Price price) {
        return priceRepository.save(price);
    }

    public void delete(Price price){
        priceRepository.delete(price);
    }

    public Price findById(Integer id) {
        return priceRepository.findById(id).get();
    }
}
