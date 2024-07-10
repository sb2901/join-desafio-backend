package com.join.test.fullstack.testfullstack.service;

import com.join.test.fullstack.testfullstack.dto.ProductDto;
import com.join.test.fullstack.testfullstack.entity.Product;
import com.join.test.fullstack.testfullstack.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDto> getAll(Long idCategory){

        Iterable<Product> it = productRepository.findByCategoryId(idCategory);
        List<ProductDto> result = new ArrayList<>();
        it.forEach(e-> result.add(convertToDto(e)));
        return result;
    }

    public List<ProductDto> getAll( ){
        Iterable<Product> it = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        it.forEach(e-> result.add(convertToDto(e)));
        return result;
    }

    public ProductDto get(Long id){
        Optional<Product> opProduct = productRepository.findById(id);
        return opProduct.map(this::convertToDto).orElse(null);
    }

    public ProductDto add(ProductDto dto) {
        Product product = convertToEntity(dto);
        product = productRepository.save(product);
        return convertToDto(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public void update(ProductDto dto) {

        Product Product = convertToEntity(dto);
        productRepository.save(Product);
    }

    private ProductDto convertToDto(Product Product) {
        ProductDto ProductDto = modelMapper.map(Product, ProductDto.class);
        return ProductDto;
    }

    private Product convertToEntity(ProductDto ProductDto)   {
        Product Product = modelMapper.map(ProductDto, Product.class);
        return Product;

    }
}
