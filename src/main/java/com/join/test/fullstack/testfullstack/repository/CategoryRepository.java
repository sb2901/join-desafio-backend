package com.join.test.fullstack.testfullstack.repository;

import com.join.test.fullstack.testfullstack.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
