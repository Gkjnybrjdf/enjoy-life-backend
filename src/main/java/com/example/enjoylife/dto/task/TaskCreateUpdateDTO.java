package com.example.enjoylife.dto.task;

import com.example.enjoylife.entity.Category;
import com.example.enjoylife.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class TaskCreateUpdateDTO {

    private String name;

    private String description;

    private Task.Priority priority;

    private Boolean easy;

    private Boolean active;

    private List<Category> categories;
}
