package com.example.enjoylife;

import com.example.enjoylife.entity.dto.category.CategoryCreateUpdateDTO;
import com.example.enjoylife.entity.dto.category.CategoryDTO;
import com.example.enjoylife.entity.dto.task.TaskCreateUpdateDTO;
import com.example.enjoylife.entity.dto.task.TaskDTO;
import com.example.enjoylife.entity.Task;
import com.example.enjoylife.service.CategoryService;
import com.example.enjoylife.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private CategoryService categoryService;

    private final List<CategoryDTO> newCategories = new ArrayList<>();
    private final TaskCreateUpdateDTO newTask = new TaskCreateUpdateDTO(
            "Тест",
            "Опсиание тест",
            Task.Priority.HIGH,
            true,
            true,
            newCategories
    );
    private final TaskCreateUpdateDTO newTask2 = new TaskCreateUpdateDTO(
            "Тест1",
            "Опсиание тест1",
            Task.Priority.HIGH,
            true,
            true,
            newCategories
    );

    @Test
    public void testTaskServiceSave_shouldCreate() {
        TaskDTO createdTask = taskService.save(newTask);

        Assert.isTrue(taskService
                        .getById(createdTask.getId())
                        .isPresent(),
                "Создание задачи не выполняется успешно");
    }

    @Test
    public void testTaskServiceGetById_shouldGetTask() {
        TaskDTO createdTask = taskService.save(newTask);

        Assert.isTrue(taskService
                        .getById(createdTask.getId())
                        .isPresent(),
                "Поиск задачи не выполняется успешно");
    }

    @Test
    public void testTaskServiceUpdate_shouldUpdateTask() {
        TaskDTO createdTask = taskService.save(newTask);

        TaskCreateUpdateDTO updateTask =
                new TaskCreateUpdateDTO(
                        "Тест1",
                        "Опсиание тест1",
                        Task.Priority.LOW,
                        false,
                        false, newCategories);

        TaskDTO updatedTask = taskService.update(createdTask.getId(), updateTask);

        Assert.isTrue(!createdTask.getActiveModifiedDate()
                        .equals(updatedTask.getActiveModifiedDate()),
                "Обновление задачи не выполняется успешно");
    }

    @Test
    public void testTaskServiceDelete_shouldDeleteTask() {
        TaskDTO createdTask = taskService.save(newTask);

        Long taskId = taskService.delete(createdTask.getId());

        Assert.isTrue(taskService.getById(taskId).isEmpty(),
                "Удаление задачи не выполняется успешно");
    }

    @Test
    public void testTaskServiceFindByCategoryId_shouldGetAllTaskByCategoryId() {
        CategoryCreateUpdateDTO newCategory =
                new CategoryCreateUpdateDTO("Тест", "Описание тест");
        CategoryDTO createdCategory = categoryService.save(newCategory);
        newCategories.add(createdCategory);

        taskService.save(newTask);
        taskService.save(newTask2);

        Assert.isTrue(taskService
                        .findByCategoryId(newCategories.get(0).getId())
                        .size() == 2,
                "Поиск задач по id категории не выполнился успешно");
    }

    @Test
    public void testTaskServiceSaveChild_shouldCreateChildTask() {
        TaskDTO createdTask = taskService.save(newTask);
        Long parentTaskId = createdTask.getId();

        TaskDTO createdChildTask = taskService.saveChild(parentTaskId, newTask2);

        Assert.isTrue(taskService.getById(createdChildTask.getId()).isPresent(),
                "Создание дочерней задачи не выполняется успешно");
    }

    @Test
    public void testTaskServiceGetChildTasks_shouldGetChildTasks() {
        TaskCreateUpdateDTO newTask3 = new TaskCreateUpdateDTO(
                "Тест2",
                "Опсиание тест2",
                Task.Priority.HIGH,
                true,
                true,
                newCategories
        );

        TaskDTO createdTask = taskService.save(newTask);
        Long parentTaskId = createdTask.getId();

        taskService.saveChild(parentTaskId, newTask2);
        taskService.saveChild(parentTaskId, newTask3);

        Assert.isTrue(taskService.getChildTasks(parentTaskId).size() == 2,
                "Получение списка дочерних задач не выполняется успешно");
    }
}