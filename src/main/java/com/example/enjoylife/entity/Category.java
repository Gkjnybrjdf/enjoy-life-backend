package com.example.enjoylife.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Entity
@Table(name = "category", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @NotBlank(message = "Введите название")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
