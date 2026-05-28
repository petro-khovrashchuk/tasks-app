package com.petrokhovrashchuk.jobinterview.model;

import java.util.Objects;

public class Task {

  private Long id;
  private String title;
  private String description;
  private boolean done;

  public Task(
      final Long id,
      final String title,
      final String description,
      final boolean done) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.done = done;
  }

  @Override
  public boolean equals(final Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Task task = (Task) o;
    return id == task.id && done == task.done && Objects.equals(title, task.title)
        && Objects.equals(description, task.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, done);
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(final boolean done) {
    this.done = done;
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", done=" + done +
        '}';
  }
}
