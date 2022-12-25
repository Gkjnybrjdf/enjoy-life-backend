package com.example.enjoylife.entity.dto.task;

import com.example.enjoylife.entity.dto.category.CategoryDTO;
import com.example.enjoylife.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TaskCreateUpdateDTO {

    private String name;

    private String description;

    private Task.Priority priority;

    private Boolean easy;

    private Boolean active;

    private List<CategoryDTO> categories;
}
