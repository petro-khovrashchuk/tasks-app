package com.petrokhovrashchuk.jobinterview.controller;

import com.petrokhovrashchuk.jobinterview.dto.ExceptionDto;
import com.petrokhovrashchuk.jobinterview.exception.TaskNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDto> handleException(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ExceptionDto(e.getMessage()));
  }

  @ExceptionHandler(TaskNotFound.class)
  public ResponseEntity<ExceptionDto> handleTaskNotFoundException(TaskNotFound e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ExceptionDto(e.getMessage()));
  }

}
