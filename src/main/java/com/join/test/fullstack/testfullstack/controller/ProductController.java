package com.join.test.fullstack.testfullstack.controller;

import com.join.test.fullstack.testfullstack.dto.ProductDto;
import com.join.test.fullstack.testfullstack.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable( value = "id") Long id){
        return ResponseEntity.ok(productService.get(id));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<ProductDto>> getAll(@PathVariable( value = "id") Long id){
        return ResponseEntity.ok(productService.getAll(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProductDto c){
        productService.update(c);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable( value = "id") Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<ProductDto> add(@RequestBody ProductDto c){
        return ResponseEntity.ok(productService.add(c));
    }
}
