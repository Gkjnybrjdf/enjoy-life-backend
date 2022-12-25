package com.example.enjoylife.document.dto;

import com.example.enjoylife.document.AuthenticationLog;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class AuthenticationLogDTO {

    private UUID id;

    private Long userId;

    private String username;

    private OffsetDateTime loginDate;

    private AuthenticationLog.State authenticationState;
}