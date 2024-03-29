package com.example.enjoylife.repo.jpa;

import com.example.enjoylife.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("from Task task join task.categories cats where cats.id = :categoryId")
    List<Task> findByCategoriesContains(Long categoryId);

    @Query("from Task task join task.parentTask parentTask where parentTask.id = :parentId")
    List<Task> findByParentId(Long parentId);
}
