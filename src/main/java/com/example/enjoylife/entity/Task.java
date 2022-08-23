package com.example.enjoylife.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "to_do_list", schema = "public")
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

    @ManyToMany
    @JoinTable(name = "category_to_do_list",
        joinColumns =  @JoinColumn(name = "to_do_list_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
