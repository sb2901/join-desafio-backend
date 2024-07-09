package com.join.test.fullstack.testfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private CategoryDto category;
}
