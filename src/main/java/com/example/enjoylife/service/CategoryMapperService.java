package com.example.enjoylife.service;

import com.example.enjoylife.dto.category.CategoryDTO;
import com.example.enjoylife.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapperService {

    public CategoryDTO mapToDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .createdAt(category.getCreatedAt())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category mapToEntity(CategoryDTO categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .createdAt(categoryDto.getCreatedAt())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }
}
