package org.economics.planningsystem.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.economics.planningsystem.dto.exception.ExceptionDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ResponseExceptionWriter {
    public void writeInJson(HttpServletResponse response, Exception exception, Integer status) throws IOException, ServletException {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        exceptionDto.setCode(status);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(status);

        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, exceptionDto);
        responseStream.flush();
    }
}
