package com.petrokhovrashchuk.jobinterview.exception;

public class TaskNotFound extends RuntimeException {

  public TaskNotFound(final String message) {
    super(message);
  }
}
