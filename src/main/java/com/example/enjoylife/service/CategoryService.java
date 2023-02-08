package com.example.enjoylife.service;

import com.example.enjoylife.entity.dto.category.CategoryCreateUpdateDTO;
import com.example.enjoylife.entity.dto.category.CategoryDTO;
import com.example.enjoylife.entity.Category;
import com.example.enjoylife.mapper.CategoryMapperService;
import com.example.enjoylife.repo.jpa.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapperService categoryMapperService;

    public CategoryDTO save(CategoryCreateUpdateDTO categoryCreateUpdateDTO) {
        Category category = Category.builder()
                .createdAt(OffsetDateTime.now())
                .name(categoryCreateUpdateDTO.getName())
                .description(categoryCreateUpdateDTO.getDescription())
                .build();

        return categoryMapperService.mapToDto(categoryRepo.save(category));
    }

    public Page<CategoryDTO> getList() {
        return categoryRepo.findAll(Pageable.unpaged())
                .map(categoryMapperService::mapToDto);
    }

    public Optional<CategoryDTO> findById(Long id) {
        return categoryRepo.findById(id)
                .map(categoryMapperService::mapToDto);
    }

    public Long delete(Long id) {
        categoryRepo.deleteById(id);

        return id;
    }

    public CategoryDTO update(Long id, CategoryCreateUpdateDTO categoryDto) {
        Category categoryFromDb = categoryRepo.getReferenceById(id);

        categoryFromDb.setDescription(categoryDto.getDescription());
        categoryFromDb.setName(categoryDto.getName());

        return categoryMapperService.mapToDto(categoryRepo.save(categoryFromDb));
    }
}
