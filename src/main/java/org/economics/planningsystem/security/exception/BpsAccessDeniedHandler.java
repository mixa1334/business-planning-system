package org.economics.planningsystem.security.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

public class BpsAccessDeniedHandler implements AccessDeniedHandler {
    private final ResponseExceptionWriter exceptionWriter;

    public BpsAccessDeniedHandler(ResponseExceptionWriter exceptionWriter) {
        this.exceptionWriter = exceptionWriter;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        exceptionWriter.writeInJson(response, accessDeniedException, SC_FORBIDDEN);
    }
}
