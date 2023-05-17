package com.daniel.task.controllers;

import com.daniel.task.entities.Task;
import com.daniel.task.exceptions.TaskException;
import com.daniel.task.jsons.CreateTaskRest;
import com.daniel.task.jsons.TaskRest;
import com.daniel.task.responses.TaskResponse;
import com.daniel.task.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskControllerTests {

    public static final TaskRest TASK_REST = new TaskRest();

    public static final Task TASK = new Task();

    public static final CreateTaskRest CREATE_TASK_REST = new CreateTaskRest();

    public static final List<TaskRest> TASK_REST_LIST = new ArrayList<>();

    public static final long TASK_ID = 1L;
    public static final String DESCRIPTION = "Task 1";
    public static final Integer DONE = 0;
    public static final Date DATE = new Date();

    public static final String SUCCESS_STATUS = "Success";
    public static final String SUCCESS_CODE = "200 OK";
    public static final String OK = "OK";

    public static final String TASK_CREATED_RESPONSE = "TASK_CREATED";
    public static final String TASK_UPDATED_RESPONSE = "TASK_UPDATED";
    public static final Long TASK_DELETED_RESPONSE = 1L;

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    @BeforeEach
    public void init() throws TaskException {
//        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);

        TASK_REST.setId(TASK_ID);
        TASK_REST.setDescription(DESCRIPTION);
        TASK_REST.setDate(DATE);
        TASK_REST.setDone(DONE);

        Mockito.when(taskService.getTaskById(TASK_ID)).thenReturn(TASK_REST);
        Mockito.when(taskService.getAll()).thenReturn(TASK_REST_LIST);
        Mockito.when(taskService.create(CREATE_TASK_REST)).thenReturn(TASK);
        Mockito.when(taskService.update(TASK_REST)).thenReturn(TASK);
        Mockito.when(taskService.deleteById(TASK_ID)).thenReturn(TASK_DELETED_RESPONSE);
    }

    @Test
    public void getTaskByIdTest() throws TaskException {
        final TaskResponse<TaskRest> response =  taskController.getTaskById(TASK_ID);
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), TASK_REST);
    }

    @Test
    public void getTasksTest() throws TaskException {
        final TaskResponse<List<TaskRest>> response =  taskController.getTasks();
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), TASK_REST_LIST);
    }

    @Test
    public void createTaskTest() throws TaskException {
        CREATE_TASK_REST.setDescription(DESCRIPTION);
        CREATE_TASK_REST.setDate(DATE);

        final TaskResponse<Task> response =  taskController.createTask(CREATE_TASK_REST);

        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), TASK);
    }

    @Test
    public void updateTaskTest() throws TaskException {

        final TaskResponse<Task> response =  taskController.updateTask(TASK_REST);

        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), TASK);
    }

    @Test
    public void deleteTaskTest() throws TaskException {

        final TaskResponse<Long> response =  taskController.deleteTask(TASK_ID);

        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), TASK_DELETED_RESPONSE);
    }
}
