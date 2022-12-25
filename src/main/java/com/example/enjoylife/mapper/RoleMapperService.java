package com.example.enjoylife.mapper;

import com.example.enjoylife.entity.dto.role.RoleDTO;
import com.example.enjoylife.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperService {
    public RoleDTO mapToDto(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .build();
    }

    public Role mapToEntity(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .createdAt(roleDTO.getCreatedAt())
                .build();
    }
}