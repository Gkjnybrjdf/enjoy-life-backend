package com.example.enjoylife.mapper;

import com.example.enjoylife.document.AuthenticationLog;
import com.example.enjoylife.document.dto.AuthenticationLogCreateDTO;
import com.example.enjoylife.document.dto.AuthenticationLogDTO;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AuthenticationLogMapperService {

    public AuthenticationLogDTO mapToDto(AuthenticationLog authenticationLog) {
        return AuthenticationLogDTO.builder()
                .id(authenticationLog.getId())
                .userId(authenticationLog.getUserId())
                .username(authenticationLog.getUsername())
                .loginDate(authenticationLog.getLoginDate())
                .authenticationState(authenticationLog.getState())
                .build();
    }

    public AuthenticationLog mapToDocument(AuthenticationLogDTO authenticationLogDTO) {
        return AuthenticationLog.builder()
                .id(authenticationLogDTO.getId())
                .userId(authenticationLogDTO.getUserId())
                .username(authenticationLogDTO.getUsername())
                .loginDate(authenticationLogDTO.getLoginDate())
                .state(authenticationLogDTO.getAuthenticationState())
                .build();
    }

    public AuthenticationLog mapToDocument(
            AuthenticationLogCreateDTO authenticationLogDTO,
            OffsetDateTime loginDate,
            UUID id
    ) {
        return AuthenticationLog.builder()
                .id(id)
                .userId(authenticationLogDTO.getUserId())
                .username(authenticationLogDTO.getUsername())
                .loginDate(loginDate)
                .state(authenticationLogDTO.getAuthenticationState())
                .build();
    }
}