package com.daniel.task.services;

import com.daniel.task.entities.Task;
import com.daniel.task.exceptions.TaskException;
import com.daniel.task.jsons.TaskRest;
import com.daniel.task.repositories.TaskRepository;
import com.daniel.task.responses.TaskResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceTests {

    public static final Task TASK = new Task();
    public static final List<Task> TASK_LIST = new ArrayList<>();


    public static final long TASK_ID = 1L;
    public static final String DESCRIPTION = "Task 1";
    public static final Integer DONE = 0;
    public static final Date DATE = new Date();

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskImpl taskImpl;

    @BeforeEach
    public void init() throws TaskException {
//        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);

        TASK.setId(TASK_ID);
        TASK.setDescription(DESCRIPTION);
        TASK.setDone(DONE);
        TASK.setDate(DATE);
    }

    @Test
    public void getTaskByIdTest() throws TaskException {
        Mockito.when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(TASK));
        taskImpl.getTaskById(TASK_ID);
    }

//    @Test(expected = TaskException.class)
//    public void getTaskByIdTestError() throws TaskException {
//        Mockito.when(taskRepository.findById(TASK_ID)).thenReturn(Optional.empty());
//        taskImpl.getTaskById(TASK_ID);
//        fail();
//    }

    @Test
    public void getAllTest() throws TaskException {
        Mockito.when(taskRepository.getAll()).thenReturn(TASK_LIST);
        taskImpl.getAll();
    }

    @Test
    public void deleteByIdTest() throws TaskException {
        Mockito.when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(TASK));
        taskImpl.deleteById(TASK_ID);
    }

}
