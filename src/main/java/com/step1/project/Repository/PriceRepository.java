package com.step1.project.Repository;

import com.step1.project.Entity.Price;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends PagingAndSortingRepository<Price, Integer> {
}
