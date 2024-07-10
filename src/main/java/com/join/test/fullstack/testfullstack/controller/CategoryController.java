package com.join.test.fullstack.testfullstack.controller;

import com.join.test.fullstack.testfullstack.dto.CategoryDto;
import com.join.test.fullstack.testfullstack.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable( value = "id") Long id){
        return ResponseEntity.ok(categoryService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CategoryDto c){
        categoryService.update(c);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable( value = "id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> add(@RequestBody CategoryDto c){
        return ResponseEntity.ok(categoryService.add(c));
    }
}
