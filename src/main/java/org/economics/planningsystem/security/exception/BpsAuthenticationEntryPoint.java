package org.economics.planningsystem.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class BpsAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ResponseExceptionWriter exceptionWriter;

    public BpsAuthenticationEntryPoint(ResponseExceptionWriter exceptionWriter) {
        this.exceptionWriter = exceptionWriter;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        exceptionWriter.writeInJson(response, authException, SC_UNAUTHORIZED);
    }
}
