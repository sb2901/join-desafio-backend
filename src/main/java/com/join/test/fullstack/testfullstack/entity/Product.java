package com.join.test.fullstack.testfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,name = "nr_id")
    private Long id;

    @Column(nullable = false,name = "ds_name")
    private String name;

    @Column(nullable = false,name = "ds_description")
    private String description;

    @ManyToOne
    @JoinColumn(name="nr_category", nullable=false)
    private Category category;

    public void loadCategoryById(Long id){
        category = new Category();
        category.setId(id);
    }

}
