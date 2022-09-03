package com.example.enjoylife.service;

import com.example.enjoylife.dto.category.CategoryCreateUpdateDTO;
import com.example.enjoylife.dto.category.CategoryDTO;
import com.example.enjoylife.entity.Category;
import com.example.enjoylife.repo.CategoryRepo;
import com.example.enjoylife.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final TaskRepo taskRepo;

    public CategoryDTO save(CategoryCreateUpdateDTO categoryCreateUpdateDTO) {
        Category category = Category.builder()
                .createdAt(OffsetDateTime.now())
                .name(categoryCreateUpdateDTO.getName())
                .description(categoryCreateUpdateDTO.getDescription())
                .build();

        return mapToDto(categoryRepo.save(category));
    }

    public Page<CategoryDTO> getList() {
        return categoryRepo.findAll(Pageable.unpaged())
                .map(this::mapToDto);
    }

    public ResponseEntity<CategoryDTO> findById(Long id) {
        return ResponseEntity.of(categoryRepo.findById(id)
                .map(this::mapToDto));
    }

    public Long delete(Long id) {
        if (!taskRepo.findByCategoriesContains(id).isEmpty())
            throw new RuntimeException("Невозможно удалить. К данной категории привязаны задачи.");

        categoryRepo.deleteById(id);

        return id;
    }

    public CategoryDTO update(Long id, CategoryCreateUpdateDTO categoryDto) {
        Category categoryFromDb = categoryRepo.getReferenceById(id);

        categoryFromDb.setDescription(categoryDto.getDescription());
        categoryFromDb.setName(categoryDto.getName());

        return mapToDto(categoryRepo.save(categoryFromDb));
    }

    public CategoryDTO mapToDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .createdAt(category.getCreatedAt())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
