package com.join.test.fullstack.testfullstack.service;

import com.join.test.fullstack.testfullstack.dto.CategoryDto;
import com.join.test.fullstack.testfullstack.entity.Category;
import com.join.test.fullstack.testfullstack.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryDto> getAll(){
        Iterable<Category> it = categoryRepository.findAll();
        List<CategoryDto> result = new ArrayList<>();
        it.forEach(e-> result.add(convertToDto(e)));
        return result;
    }

    public CategoryDto get(Long id){
        Optional<Category> opCategory = categoryRepository.findById(id);
        return opCategory.map(this::convertToDto).orElse(null);
    }

    public CategoryDto add(CategoryDto dto) {
        Category category = convertToEntity(dto);
        category = categoryRepository.save(category);
        return convertToDto(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

   /* private void removeOldContacts(CostumerDto dto){
        Optional<Costumer>  opCostumer = userRepository.findById(dto.getId());
        opCostumer.ifPresent(costumer ->
                {
                    if(costumer.getContact()!=null && !costumer.getContact().isEmpty()) {
                        Set<Integer> collectBaseIds = dto.getContact()
                                .stream().map(c -> c.getId()).collect(Collectors.toSet());
                        costumer.getContact().forEach(contact ->{
                            if( !collectBaseIds.contains(contact.getId())){
                                contactRepository.delete(contact);
                            }
                        });
                    }
                }
        );
    }*/

    public void update(CategoryDto dto) {

        //Remove os contatos que não estão mais no dto
      //  removeOldContacts(dto);
        Category category = convertToEntity(dto);
        categoryRepository.save(category);
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    private Category convertToEntity(CategoryDto categoryDto)   {
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;

    }
}
