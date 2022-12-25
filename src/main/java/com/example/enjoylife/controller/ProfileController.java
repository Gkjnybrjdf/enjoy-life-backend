package com.example.enjoylife.controller;

import com.example.enjoylife.entity.dto.user.UserCreateUpdateDTO;
import com.example.enjoylife.entity.dto.user.UserDTO;
import com.example.enjoylife.entity.User;
import com.example.enjoylife.mapper.UserMapperService;
import com.example.enjoylife.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final UserMapperService userMapperService;

    @PostMapping("/registration")
    public UserDTO register(@RequestBody UserCreateUpdateDTO userCreateUpdateDTO) {
        return userService.save(userCreateUpdateDTO);
    }

    @GetMapping("/current")
    public UserDTO current(
            @AuthenticationPrincipal User user
    ) {
        return userMapperService.mapToDto(user);
    }
}