package com.nova.recycle.recycleme.domain.security.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<?> handlerAccessDeniedException(AccessDeniedExcption accessDeniedExcption) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(accessDeniedExcption.getMessage());
    }
}
