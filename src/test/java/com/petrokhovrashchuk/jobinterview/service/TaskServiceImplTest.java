package com.petrokhovrashchuk.jobinterview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.petrokhovrashchuk.jobinterview.exception.TaskNotFound;
import com.petrokhovrashchuk.jobinterview.model.Task;
import com.petrokhovrashchuk.jobinterview.repository.TaskRepository;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class TaskServiceImplTest {

  @MockitoBean
  private TaskRepository taskRepository;

  @Autowired
  private TaskServiceImpl taskService;

  /**
   * Test for TaskService's createTask method. Verifies task creation with valid data.
   */
  @Test
  public void testCreateTask() {
    Task task = new Task(null, "Sample Task", "This is a sample task description", false);

    Task createdTask = taskService.createTask(task);

    assertNotNull(createdTask.getId());
    assertEquals(Long.valueOf(1L), createdTask.getId());
    assertEquals("Sample Task", createdTask.getTitle());
    assertEquals("This is a sample task description", createdTask.getDescription());
    assertFalse(createdTask.isCompleted());
    verify(taskRepository).put(1L, task);
  }

  /**
   * Test for TaskService's createTask method. Ensures that size is called twice during task
   * creation for id generation.
   */
  @Test
  public void testCreateTaskGeneratesUniqueIdIsCalledTwice() {
    Task firstTask = new Task(null, "First Task", "First task description", false);
    Task secondTask = new Task(null, "Second Task", "Second task description", false);

    taskService.createTask(firstTask);
    taskService.createTask(secondTask);

    verify(taskRepository, times(2)).size();
  }

  @Test
  public void testGetAllTasksReturnsAllRepositoryValues() {
    Task firstTask = new Task(1L, "First Task", "First task description", false);
    Task secondTask = new Task(2L, "Second Task", "Second task description", true);
    when(taskRepository.values()).thenReturn(List.of(firstTask, secondTask));

    Collection<Task> allTasks = taskService.getAllTasks();

    assertEquals(2, allTasks.size());
    assertTrue(allTasks.contains(firstTask));
    assertTrue(allTasks.contains(secondTask));
    verify(taskRepository).values();
  }

  @Test
  public void testCompleteTaskMarksExistingTaskAsCompleted() {
    Task task = new Task(7L, "Existing Task", "Existing task description", false);
    when(taskRepository.containsKey(7L)).thenReturn(true);
    when(taskRepository.get(7L)).thenReturn(task);

    Task completedTask = taskService.completeTask(7L);

    assertSame(task, completedTask);
    assertEquals(Long.valueOf(7L), completedTask.getId());
    assertTrue(completedTask.isCompleted());
    verify(taskRepository).containsKey(7L);
    verify(taskRepository).get(7L);
  }

  @Test
  public void testCompleteTaskThrowsWhenTaskIsMissing() {
    when(taskRepository.containsKey(99L)).thenReturn(false);

    TaskNotFound exception = assertThrows(TaskNotFound.class, () -> taskService.completeTask(99L));

    assertEquals("Task with id 99 not found", exception.getMessage());
    verify(taskRepository).containsKey(99L);
  }


}
