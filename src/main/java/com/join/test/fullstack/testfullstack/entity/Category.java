package com.join.test.fullstack.testfullstack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,name = "nr_id")
    private Long id;

    @Column(nullable = false,name = "ds_name")
    private String name;

    @Column(nullable = false,name = "ds_description")
    private String description;

    @OneToMany(mappedBy="category", cascade= CascadeType.ALL)
    private Set<Product> product;

}
