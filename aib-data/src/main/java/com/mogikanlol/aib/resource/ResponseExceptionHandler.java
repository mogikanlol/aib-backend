package com.mogikanlol.aib.resource;

import com.mogikanlol.aib.service.BoardNotFoundException;
import com.mogikanlol.aib.service.PostNotFoundException;
import com.mogikanlol.aib.service.ThreadNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleBoardNotFoundException(BoardNotFoundException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleThreadNotFoundException(ThreadNotFoundException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handlePostNotFoundException(PostNotFoundException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
}
