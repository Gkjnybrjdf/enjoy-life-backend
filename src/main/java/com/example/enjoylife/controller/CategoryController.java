package com.example.enjoylife.controller;

import com.example.enjoylife.dto.category.CategoryCreateUpdateDTO;
import com.example.enjoylife.dto.category.CategoryDTO;
import com.example.enjoylife.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryDTO create(@RequestBody CategoryCreateUpdateDTO categoryCreateUpdateDTO) {
        return categoryService.save(categoryCreateUpdateDTO);
    }

    @GetMapping("/list")
    public Page<CategoryDTO> list() {
        return categoryService.getList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.of(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(
            @PathVariable Long id,
            @RequestBody CategoryCreateUpdateDTO categoryDto
    ) {
        return categoryService.update(id, categoryDto);
    }
}
