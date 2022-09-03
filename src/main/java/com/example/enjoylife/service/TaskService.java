package com.example.enjoylife.service;

import com.example.enjoylife.dto.task.TaskCreateUpdateDTO;
import com.example.enjoylife.dto.task.TaskDTO;
import com.example.enjoylife.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import com.example.enjoylife.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;

    public TaskDTO save(TaskCreateUpdateDTO taskCreateUpdateDTO) {
        Task task = Task.builder()
                .createdAt(OffsetDateTime.now())
                .name(taskCreateUpdateDTO.getName())
                .description(taskCreateUpdateDTO.getDescription())
                .priority(taskCreateUpdateDTO.getPriority())
                .easy(taskCreateUpdateDTO.getEasy())
                .active(taskCreateUpdateDTO.getActive())
                .activeModifiedDate(OffsetDateTime.now())
                .categories(taskCreateUpdateDTO.getCategories())
                .build();

        return mapToDto(taskRepo.save(task));
    }

    public Page<TaskDTO> getList() {
        return taskRepo.findAll(Pageable.unpaged())
                .map(this::mapToDto);
    }

    public ResponseEntity<TaskDTO> getById(Long id) {
        return ResponseEntity.of(taskRepo.findById(id)
                .map(this::mapToDto));
    }

    public TaskDTO update(Long id, TaskCreateUpdateDTO taskCreateUpdateDTO) {
        Task task = taskRepo.getReferenceById(id);

        if (taskCreateUpdateDTO.getActive() != task.getActive()) {
            task.setActiveModifiedDate(OffsetDateTime.now());
        }

        task.setName(taskCreateUpdateDTO.getName());
        task.setDescription(taskCreateUpdateDTO.getDescription());
        task.setPriority(taskCreateUpdateDTO.getPriority());
        task.setEasy(taskCreateUpdateDTO.getEasy());
        task.setActive(taskCreateUpdateDTO.getActive());
        task.setCategories(taskCreateUpdateDTO.getCategories());

        return mapToDto(taskRepo.save(task));
    }

    public Long delete(Long id) {
        taskRepo.deleteById(id);

        return id;
    }

    public List<TaskDTO> findByCategoryId(Long id) {
        return taskRepo.findByCategoriesContains(id)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TaskDTO saveChild(Long parentTaskId, TaskCreateUpdateDTO childTask) {
        Task task = Task.builder()
                .createdAt(OffsetDateTime.now())
                .name(childTask.getName())
                .description(childTask.getDescription())
                .priority(childTask.getPriority())
                .easy(childTask.getEasy())
                .active(childTask.getActive())
                .activeModifiedDate(OffsetDateTime.now())
                .categories(childTask.getCategories())
                .task(taskRepo.findById(parentTaskId).orElse(null))
                .build();

        return mapToDto(taskRepo.save(task));
    }

    public List<TaskDTO> getChildTasks(Long parentTaskId) {
        return taskRepo.findByParentId(parentTaskId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TaskDTO mapToDto(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .createdAt(task.getCreatedAt())
                .name(task.getName())
                .description(task.getDescription())
                .priority(task.getPriority())
                .easy(task.getEasy())
                .active(task.getActive())
                .activeModifiedDate(task.getActiveModifiedDate())
                .task(task.getTask())
                .categories(task.getCategories())
                .build();
    }
}
