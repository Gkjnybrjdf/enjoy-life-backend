package com.example.enjoylife.mapper;

import com.example.enjoylife.entity.dto.user.UserDTO;
import com.example.enjoylife.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapperService {
    private final RoleMapperService roleMapperService;

    public UserDTO mapToDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .password(user.getPassword())
                .roles(Optional
                        .ofNullable(user.getRoles())
                        .map(roles -> roles.stream()
                                .map(roleMapperService::mapToDto)
                                .collect(Collectors.toSet()))
                        .orElse(null))
                .build();
    }
}