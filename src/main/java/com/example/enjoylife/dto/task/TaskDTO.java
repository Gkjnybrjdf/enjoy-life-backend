package com.example.enjoylife.dto.task;

import com.example.enjoylife.dto.category.CategoryDTO;
import com.example.enjoylife.entity.Task;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class TaskDTO {

    private Long id;

    private OffsetDateTime createdAt;

    private String name;

    private String description;

    private Task.Priority priority;

    private Boolean easy;

    private Boolean active;

    private OffsetDateTime activeModifiedDate;

    private Long parentTaskId;

    private List<CategoryDTO> categories;
}
