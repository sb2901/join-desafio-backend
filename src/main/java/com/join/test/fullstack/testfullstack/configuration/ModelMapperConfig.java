package com.join.test.fullstack.testfullstack.configuration;

import com.join.test.fullstack.testfullstack.dto.CategoryDto;
import com.join.test.fullstack.testfullstack.dto.ProductDto;
import com.join.test.fullstack.testfullstack.entity.Category;
import com.join.test.fullstack.testfullstack.entity.Product;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        //Não mapear os filhos @OneToMany
        modelMapper
                .getConfiguration()
                .setPropertyCondition(
                        context -> !(context.getSource() instanceof PersistentCollection));

        //Product->ProductDto : Category->Long
        TypeMap<Product, ProductDto> propertyMapper = modelMapper.createTypeMap(Product.class, ProductDto.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getCategory().getId(), ProductDto::setCategoryId)
        );

        TypeMap<ProductDto,Product> propertyMapper2 = modelMapper.createTypeMap(ProductDto.class,Product.class);
        propertyMapper2.addMappings(
                mapper -> mapper.map(src -> src.getCategoryId(), Product::loadCategoryById)
        );

        return modelMapper;
    }

}
