package com.example.enjoylife.entity.dto.user;

import com.example.enjoylife.entity.dto.role.RoleDTO;
import lombok.Data;

import java.util.Set;

@Data
public class UserCreateUpdateDTO {

    private String username;

    private String password;

    private Set<RoleDTO> roles;
}