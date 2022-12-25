package com.example.enjoylife.entity.dto.role;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class RoleDTO {

    private Long id;

    private String name;

    private OffsetDateTime createdAt;
}