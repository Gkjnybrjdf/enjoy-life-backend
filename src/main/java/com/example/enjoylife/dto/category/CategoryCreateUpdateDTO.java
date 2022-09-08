package com.example.enjoylife.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryCreateUpdateDTO {

    private String name;

    private String description;
}
