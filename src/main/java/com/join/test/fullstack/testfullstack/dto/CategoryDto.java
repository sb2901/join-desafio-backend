package com.join.test.fullstack.testfullstack.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private List<ProductDto> product;
}
