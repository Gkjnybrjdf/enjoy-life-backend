package com.example.enjoylife.configuration.security;

import com.example.enjoylife.document.AuthenticationLog;
import com.example.enjoylife.document.dto.AuthenticationLogCreateDTO;
import com.example.enjoylife.service.AuthenticationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Primary
@RequiredArgsConstructor
public class LoggingAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final AuthenticationLogService authenticationLogService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        authenticationLogService.save(AuthenticationLogCreateDTO.builder()
                .username(request.getParameterValues("username")[0])
                .authenticationState(AuthenticationLog.State.AUTHENTICATION_FAILURE)
                .build());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}