package com.ninos.service.admin.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ninos.model.dto.CategoryDTO;
import com.ninos.model.entity.Category;
import com.ninos.repository.CategoryRepository;


@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(category.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);
    }



}