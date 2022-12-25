package com.example.enjoylife.entity.dto.task;

import com.example.enjoylife.entity.dto.category.CategoryCreateUpdateDTO;
import com.example.enjoylife.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskUploadDTO {

    private String name;

    private String description;

    private Task.Priority priority;

    private Boolean easy;

    private Boolean active;

    private List<CategoryCreateUpdateDTO> categories;

    private List<TaskUploadDTO> children;
}
