package com.petrokhovrashchuk.jobinterview.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.petrokhovrashchuk.jobinterview.dto.TaskDto;
import com.petrokhovrashchuk.jobinterview.model.Task;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskMapperImplTest {

  @Test
  public void testModelsToDtosReturnsEmptyListWhenInputIsEmpty() {
    TaskMapperImpl mapper = new TaskMapperImpl();
    List<Task> tasks = Collections.emptyList();

    List<TaskDto> taskDtos = (List<TaskDto>) mapper.modelsToDtos(tasks);

    assertTrue(taskDtos.isEmpty(), "Expected empty list when input list is empty");
  }

  @Test
  public void testModelsToDtosConvertsSingleTaskToDto() {
    TaskMapperImpl mapper = new TaskMapperImpl();
    Task task = new Task(1L, "Test Task", "Test Description", false);

    List<TaskDto> taskDtos = (List<TaskDto>) mapper.modelsToDtos(List.of(task));

    assertEquals(1, taskDtos.size(), "Expected list of size 1");
    TaskDto taskDto = taskDtos.get(0);
    assertEquals(task.getId(), taskDto.id(), "Expected matching IDs");
    assertEquals(task.getTitle(), taskDto.title(), "Expected matching titles");
    assertEquals(task.getDescription(), taskDto.description(), "Expected matching descriptions");
    assertEquals(task.isDone(), taskDto.done(), "Expected matching done status");
  }

  @Test
  public void testModelsToDtosConvertsMultipleTasksToDtos() {
    TaskMapperImpl mapper = new TaskMapperImpl();
    Task task1 = new Task(1L, "Task 1", "Description 1", false);
    Task task2 = new Task(2L, "Task 2", "Description 2", true);

    List<TaskDto> taskDtos = (List<TaskDto>) mapper.modelsToDtos(List.of(task1, task2));

    assertEquals(2, taskDtos.size(), "Expected list of size 2");

    TaskDto taskDto1 = taskDtos.get(0);
    assertEquals(task1.getId(), taskDto1.id(), "Expected matching IDs for task 1");
    assertEquals(task1.getTitle(), taskDto1.title(), "Expected matching titles for task 1");
    assertEquals(task1.getDescription(), taskDto1.description(),
        "Expected matching descriptions for task 1");
    assertEquals(task1.isDone(), taskDto1.done(),
        "Expected matching done status for task 1");

    TaskDto taskDto2 = taskDtos.get(1);
    assertEquals(task2.getId(), taskDto2.id(), "Expected matching IDs for task 2");
    assertEquals(task2.getTitle(), taskDto2.title(), "Expected matching titles for task 2");
    assertEquals(task2.getDescription(), taskDto2.description(),
        "Expected matching descriptions for task 2");
    assertEquals(task2.isDone(), taskDto2.done(),
        "Expected matching done status for task 2");
  }

}
