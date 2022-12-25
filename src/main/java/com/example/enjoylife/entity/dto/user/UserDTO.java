package com.example.enjoylife.entity.dto.user;

import com.example.enjoylife.entity.dto.role.RoleDTO;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
public class UserDTO {

    private Long id;

    private String username;

    private OffsetDateTime createdAt;

    private String password;

    private Set<RoleDTO> roles;
}