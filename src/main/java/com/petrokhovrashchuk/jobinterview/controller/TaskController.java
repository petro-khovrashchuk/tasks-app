package com.petrokhovrashchuk.jobinterview.controller;

import com.petrokhovrashchuk.jobinterview.dto.TaskDto;
import com.petrokhovrashchuk.jobinterview.mapper.TaskMapper;
import com.petrokhovrashchuk.jobinterview.model.Task;
import com.petrokhovrashchuk.jobinterview.service.TaskService;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;
  private final TaskMapper taskMapper;

  public TaskController(final TaskService taskService, final TaskMapper taskMapper) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  @GetMapping
  public Collection<TaskDto> getAllTasks() {
    return taskMapper.modelsToDtos(taskService.getAllTasks());
  }

  @PostMapping
  public TaskDto createTask(@RequestBody final TaskDto requestDto) {
    Task model = taskMapper.dtoToModel(requestDto);
    return taskMapper.modelToDto(taskService.createTask(model));
  }

  @PutMapping("/{id}/complete")
  public TaskDto completeTask(@PathVariable final long id) {
    return taskMapper.modelToDto(taskService.completeTask(id));
  }

}
