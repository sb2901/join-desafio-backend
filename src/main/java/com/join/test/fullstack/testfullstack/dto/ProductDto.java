package com.join.test.fullstack.testfullstack.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
}
