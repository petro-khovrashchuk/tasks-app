package com.petrokhovrashchuk.jobinterview.service;

import com.petrokhovrashchuk.jobinterview.exception.TaskNotFound;
import com.petrokhovrashchuk.jobinterview.model.Task;
import com.petrokhovrashchuk.jobinterview.repository.TaskRepository;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  private TaskServiceImpl(final TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Collection<Task> getAllTasks() {
    return taskRepository.values();
  }

  @Override
  public Task createTask(final Task task) {
    task.setId(generateId());
    taskRepository.put(task.getId(), task);
    return task;
  }

  private Long generateId() {
    return taskRepository.size() + 1L;
  }

  @Override
  public Task markDone(final long id) {
    if (!taskRepository.containsKey(id)) {
      throw new TaskNotFound(String.format("Task with id %d not found", id));
    }

    final Task task = taskRepository.get(id);
    task.setDone(true);

    return task;
  }
}
