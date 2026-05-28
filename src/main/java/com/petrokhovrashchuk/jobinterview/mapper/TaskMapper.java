package com.petrokhovrashchuk.jobinterview.mapper;

import com.petrokhovrashchuk.jobinterview.dto.TaskDto;
import com.petrokhovrashchuk.jobinterview.model.Task;
import java.util.Collection;

public interface TaskMapper {

  TaskDto modelToDto(final Task task);

  Collection<TaskDto> modelsToDtos(final Collection<Task> tasks);

  Task dtoToModel(final TaskDto taskDto);

}
