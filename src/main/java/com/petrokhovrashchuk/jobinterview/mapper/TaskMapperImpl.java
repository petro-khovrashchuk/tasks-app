package com.petrokhovrashchuk.jobinterview.mapper;

import com.petrokhovrashchuk.jobinterview.dto.TaskDto;
import com.petrokhovrashchuk.jobinterview.model.Task;
import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

  public TaskDto modelToDto(final Task task) {
    return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
  }

  public Collection<TaskDto> modelsToDtos(final Collection<Task> tasks) {
    return tasks.stream().map(this::modelToDto).toList();
  }

  public Task dtoToModel(final TaskDto taskDto) {
    return new Task(taskDto.id(), taskDto.title(),
        taskDto.description(), taskDto.completed());
  }

}
