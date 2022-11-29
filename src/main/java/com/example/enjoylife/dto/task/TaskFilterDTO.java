package com.example.enjoylife.dto.task;

import com.example.enjoylife.entity.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskFilterDTO {
    private String name;

    private Task.Priority priority;

    private Boolean easy;

    private Boolean active;

    private Long parentTaskId;
}