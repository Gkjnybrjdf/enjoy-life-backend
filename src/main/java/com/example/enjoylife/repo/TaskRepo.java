package com.example.enjoylife.repo;

import com.example.enjoylife.entity.Category;
import com.example.enjoylife.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("from Task task join task.categories cats where cats.id = :categoryId")
    List<Task> findByCategoriesContains(Long categoryId);
}
