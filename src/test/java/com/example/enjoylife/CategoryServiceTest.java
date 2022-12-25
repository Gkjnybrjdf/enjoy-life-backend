package com.example.enjoylife;

import com.example.enjoylife.entity.dto.category.CategoryCreateUpdateDTO;
import com.example.enjoylife.entity.dto.category.CategoryDTO;
import com.example.enjoylife.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    private final CategoryCreateUpdateDTO newCategory =
            new CategoryCreateUpdateDTO("Тест", "Описание тест");

    @Test
    public void testCategoryServiceSave_shouldCreate() {
        CategoryDTO createdCategory = categoryService.save(newCategory);

        Assert.isTrue(categoryService
                        .findById(createdCategory.getId())
                        .isPresent(),
                "Категория не создана успешно");
    }

    @Test
    public void testCategoryServiceFindById_shouldGetCategory() {
        CategoryDTO createdCategory = categoryService.save(newCategory);

        Assert.isTrue(categoryService
                        .findById(createdCategory.getId())
                        .isPresent(),
                "Категория не найдена успешно");
    }

    @Test
    public void testCategoryServiceDelete_shouldDeleteCategory() {
        CategoryDTO createdCategory = categoryService.save(newCategory);

        Long categoryId = categoryService
                .delete(createdCategory.getId());

        Assert.isTrue(categoryService
                        .findById(categoryId).isEmpty(),
                "Категория не удалена успешно");
    }

    @Test
    public void testCategoryServiceUpdate_shouldUpdateCategory() {
        CategoryDTO createdCategory = categoryService.save(newCategory);

        CategoryCreateUpdateDTO updateCategory =
                new CategoryCreateUpdateDTO("Тест1", "Описание тест1");

        Long categoryId = createdCategory.getId();

        CategoryDTO updatedCategory = categoryService.update(categoryId, updateCategory);

        Assert.isTrue(!(createdCategory.getName().equals(updatedCategory.getName())),
                "Категория не обновлена успешно");
    }
}
