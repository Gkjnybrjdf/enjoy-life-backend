package com.example.enjoylife.service;

import com.example.enjoylife.dto.task.TaskCreateUpdateDTO;
import com.example.enjoylife.dto.task.TaskDTO;
import com.example.enjoylife.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import com.example.enjoylife.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapperService taskMapperService;
    private final CategoryMapperService categoryMapperService;

    public TaskDTO save(TaskCreateUpdateDTO taskCreateUpdateDTO) {
        Task task = Task.builder()
                .createdAt(OffsetDateTime.now())
                .name(taskCreateUpdateDTO.getName())
                .description(taskCreateUpdateDTO.getDescription())
                .priority(taskCreateUpdateDTO.getPriority())
                .easy(taskCreateUpdateDTO.getEasy())
                .active(taskCreateUpdateDTO.getActive())
                .activeModifiedDate(OffsetDateTime.now())
                .categories((taskCreateUpdateDTO
                        .getCategories()
                        .stream()
                        .map(categoryMapperService::mapToEntity)
                        .collect(Collectors.toList())))
                .build();

        return taskMapperService.mapToDto(taskRepo.save(task));
    }

    public Page<TaskDTO> getList() {
        return taskRepo.findAll(Pageable.unpaged())
                .map(taskMapperService::mapToDto);
    }

    public Optional<TaskDTO> getById(Long id) {
        return taskRepo.findById(id)
                .map(taskMapperService::mapToDto);
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
        task.setCategories(taskCreateUpdateDTO
                .getCategories()
                .stream()
                .map(categoryMapperService::mapToEntity)
                .collect(Collectors.toList()));

        return taskMapperService.mapToDto(taskRepo.save(task));
    }

    public Long delete(Long id) {
        taskRepo.deleteById(id);

        return id;
    }

    public List<TaskDTO> findByCategoryId(Long id) {
        return taskRepo.findByCategoriesContains(id)
                .stream()
                .map(taskMapperService::mapToDto)
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
                .categories(childTask
                        .getCategories()
                        .stream()
                        .map(categoryMapperService::mapToEntity)
                        .collect(Collectors.toList()))
                .parentTask(taskRepo.getReferenceById(parentTaskId))
                .build();

        return taskMapperService.mapToDto(taskRepo.save(task));
    }

    public List<TaskDTO> getChildTasks(Long parentTaskId) {
        return taskRepo.findByParentId(parentTaskId)
                .stream()
                .map(taskMapperService::mapToDto)
                .collect(Collectors.toList());
    }


}
