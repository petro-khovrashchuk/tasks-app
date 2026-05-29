package com.petrokhovrashchuk.jobinterview.controller;

import com.petrokhovrashchuk.jobinterview.dto.ExceptionDto;
import com.petrokhovrashchuk.jobinterview.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ExceptionDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ExceptionDto(e.getMessage()));
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<ExceptionDto> handleTaskNotFoundException(TaskNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ExceptionDto(e.getMessage()));
  }

}
