package com.example.enjoylife.controller;

import com.example.enjoylife.dto.task.TaskCreateUpdateDTO;
import com.example.enjoylife.dto.task.TaskDTO;
import com.example.enjoylife.dto.task.TaskFilterDTO;
import com.example.enjoylife.dto.task.TaskUploadDTO;
import com.example.enjoylife.service.TaskService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public TaskDTO create(@RequestBody TaskCreateUpdateDTO taskCreateUpdateDTO) {
        return taskService.save(taskCreateUpdateDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(@PathVariable Long id) {
        return ResponseEntity.of(taskService.getById(id));
    }

    @GetMapping("/list")
    public Page<TaskDTO> list() {
        return taskService.getList();
    }

    @PostMapping("/filter")
    public List<TaskDTO> filter(@RequestBody TaskFilterDTO taskFilterDTO) {
        return taskService.findByFilter(taskFilterDTO);
    }

    @GetMapping("/filter/{id}")
    public List<TaskDTO> getByCategoryId(@PathVariable Long id) {
        return taskService.findByCategoryId(id);
    }

    @PutMapping("/{id}")
    public TaskDTO update(
            @PathVariable Long id,
            @RequestBody TaskCreateUpdateDTO taskCreateUpdateDTO) {
        return taskService.update(id, taskCreateUpdateDTO);
    }

    @DeleteMapping({"/{id}"})
    public Long delete(@PathVariable Long id) {
        return taskService.delete(id);
    }

    @PostMapping("/child/{parentTaskId}")
    public TaskDTO createChildTask(@PathVariable Long parentTaskId,
                                   @RequestBody TaskCreateUpdateDTO childTask) {
        return taskService.saveChild(parentTaskId, childTask);
    }

    @GetMapping("/child/{parentTaskId}")
    public List<TaskDTO> getChildTasks(@PathVariable Long parentTaskId) {
        return taskService.getChildTasks(parentTaskId);
    }

    @PostMapping("/upload")
    public List<Long> uploadTasksFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<TaskUploadDTO> tasks = objectMapper.readValue(file.getInputStream(), new TypeReference<>() {
        });

        return taskService.uploadFromFile(tasks);
    }
}
