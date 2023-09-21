package com.rehome.chat.exception;

import com.rehome.chat.util.ChatErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ChatExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ChatBadRequestException.class)
    protected ResponseEntity<ChatErrorResponse> handleAppointmentException(RuntimeException ex, WebRequest request) {

      ChatErrorResponse errors = new ChatErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setErrorMessage(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
