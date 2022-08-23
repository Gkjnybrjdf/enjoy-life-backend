package com.example.enjoylife.dto.todolist;

import com.example.enjoylife.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class TaskCreateUpdateDTO {


    private String name;

    private String description;

    private List<Category> categories;
}
