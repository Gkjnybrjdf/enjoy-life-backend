package com.example.enjoylife.service;

import com.example.enjoylife.dto.category.CategoryDTO;
import com.example.enjoylife.dto.task.TaskCreateUpdateDTO;
import com.example.enjoylife.dto.task.TaskDTO;
import com.example.enjoylife.dto.task.TaskUploadDTO;
import com.example.enjoylife.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskMapperService {

    private final CategoryMapperService categoryMapperService;

    public TaskDTO mapToDto(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .createdAt(task.getCreatedAt())
                .name(task.getName())
                .description(task.getDescription())
                .priority(task.getPriority())
                .easy(task.getEasy())
                .active(task.getActive())
                .activeModifiedDate(task.getActiveModifiedDate())
                .parentTaskId(Optional
                        .ofNullable(task.getParentTask())
                        .map(Task::getId)
                        .orElse(null))
                .categories(Optional
                        .ofNullable(task.getCategories())
                        .map(categories -> categories.stream()
                                .map(categoryMapperService::mapToDto)
                                .collect(Collectors.toList()))
                        .orElse(null))
                .build();
    }

    public TaskCreateUpdateDTO uploadToCreateDto(TaskUploadDTO task, List<CategoryDTO> category) {
        return TaskCreateUpdateDTO.builder()
                .name(task.getName())
                .description(task.getDescription())
                .priority(task.getPriority())
                .easy(task.getEasy())
                .active(task.getActive())
                .categories(category)
                .build();

    }
}
