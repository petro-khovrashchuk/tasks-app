package com.petrokhovrashchuk.jobinterview.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.petrokhovrashchuk.jobinterview.dto.TaskDto;
import com.petrokhovrashchuk.jobinterview.mapper.TaskMapper;
import com.petrokhovrashchuk.jobinterview.model.Task;
import com.petrokhovrashchuk.jobinterview.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private TaskService taskService;

  @MockitoBean
  private TaskMapper taskMapper;

  @Test
  void whenValidInput_thenReturns200AndTask() throws Exception {
    TaskDto createdTaskDto = new TaskDto(1L, "Test Title", "Test Description", false);
    Task task = new Task(null, "Test Title", "Test Description", false);
    Task createdTask = new Task(1L, "Test Title", "Test Description", false);

    when(taskMapper.dtoToModel(any(TaskDto.class))).thenReturn(task);
    when(taskService.createTask(any(Task.class))).thenReturn(createdTask);
    when(taskMapper.modelToDto(any())).thenReturn(createdTaskDto);

    mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"id":null,"title":"Test Title","description":"Test Description","done":false}
                """)
        )
        .andExpect(status().isOk())
        .andExpect(content().json("""
            {"id":1,"title":"Test Title","description":"Test Description","done":false}
            """));
  }

  @Test
  void whenInvalidInput_thenReturns400() throws Exception {
    mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{")
        )
        .andExpect(status().isBadRequest());
  }

}
