package com.join.test.fullstack.testfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private List<ProductDto> product;
}
