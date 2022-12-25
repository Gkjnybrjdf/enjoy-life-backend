package com.example.enjoylife.service;

import com.example.enjoylife.document.dto.AuthenticationLogCreateDTO;
import com.example.enjoylife.document.dto.AuthenticationLogDTO;
import com.example.enjoylife.mapper.AuthenticationLogMapperService;
import com.example.enjoylife.repo.elasticsearch.AuthenticationLogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationLogService {
    private final AuthenticationLogRepo authenticationLogRepo;
    private final AuthenticationLogMapperService authenticationLogMapperService;

    public AuthenticationLogDTO save(AuthenticationLogCreateDTO authenticationLogCreateDTO) {
        return Optional.ofNullable(authenticationLogCreateDTO)
                .map(dto -> authenticationLogMapperService.mapToDocument(dto, OffsetDateTime.now(), UUID.randomUUID()))
                .map(authenticationLogRepo::save)
                .map(authenticationLogMapperService::mapToDto)
                .orElse(null);
    }
}