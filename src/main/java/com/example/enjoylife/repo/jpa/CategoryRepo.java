package com.example.enjoylife.repo.jpa;

import com.example.enjoylife.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
