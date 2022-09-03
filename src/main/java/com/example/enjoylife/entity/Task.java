package com.example.enjoylife.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "task", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "is_easy")
    private Boolean easy;

    @NotNull
    @Column(name = "is_active")
    private Boolean active;

    @NotNull
    @Column(name = "active_modified_date")
    private OffsetDateTime activeModifiedDate;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parent_task_id", referencedColumnName = "id")
    })
    private Task task;

    @ManyToMany
    @JoinTable(name = "category_task",
        joinColumns =  @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public enum Priority {
        LOW, MEDIUM, HIGH;
    }
}
