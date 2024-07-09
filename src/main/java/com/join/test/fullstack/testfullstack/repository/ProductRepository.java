package com.join.test.fullstack.testfullstack.repository;

import com.join.test.fullstack.testfullstack.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByCategoryId(Long id);
}
