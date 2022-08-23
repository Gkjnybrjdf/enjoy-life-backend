package com.example.enjoylife.dto.todolist;

import com.example.enjoylife.entity.Category;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class TaskDTO {

    private Long id;

    private OffsetDateTime createdAt;

    private String name;

    private String description;

    private List<Category> categories;
}
