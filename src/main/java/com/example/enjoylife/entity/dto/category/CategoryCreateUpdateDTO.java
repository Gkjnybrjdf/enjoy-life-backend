package com.example.enjoylife.entity.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CategoryCreateUpdateDTO {

    private String name;

    private String description;
}
