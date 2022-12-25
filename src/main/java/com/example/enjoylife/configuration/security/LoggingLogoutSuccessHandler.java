package com.example.enjoylife.configuration.security;

import com.example.enjoylife.document.AuthenticationLog;
import com.example.enjoylife.document.dto.AuthenticationLogCreateDTO;
import com.example.enjoylife.entity.User;
import com.example.enjoylife.service.AuthenticationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Primary
@RequiredArgsConstructor
public class LoggingLogoutSuccessHandler implements LogoutSuccessHandler {

    private final AuthenticationLogService authenticationLogService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();

        authenticationLogService.save(AuthenticationLogCreateDTO.builder().
                userId(user.getId())
                .username(user.getUsername())
                .authenticationState(AuthenticationLog.State.LOGOUT_SUCCESS)
                .build());

        response.setStatus(HttpStatus.OK.value());
    }
}