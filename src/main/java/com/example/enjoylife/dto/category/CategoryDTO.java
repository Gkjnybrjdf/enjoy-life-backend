package com.example.enjoylife.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    private OffsetDateTime createdAt;

    private String name;

    private String description;
}
