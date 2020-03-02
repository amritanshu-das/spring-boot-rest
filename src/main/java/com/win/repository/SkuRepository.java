package com.win.repository;

import com.win.model.Sku;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends PagingAndSortingRepository<Sku, Long> {

}