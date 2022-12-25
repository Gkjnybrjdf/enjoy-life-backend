package com.example.enjoylife.document.dto;

import com.example.enjoylife.document.AuthenticationLog;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationLogCreateDTO {

    private Long userId;

    private String username;

    private AuthenticationLog.State authenticationState;
}