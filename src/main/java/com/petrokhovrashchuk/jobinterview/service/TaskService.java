package com.petrokhovrashchuk.jobinterview.service;

import com.petrokhovrashchuk.jobinterview.model.Task;
import java.util.Collection;

public interface TaskService {

  Collection<Task> getAllTasks();

  Task createTask(Task task);

  Task completeTask(long id);
}
