package com.daniel.task.services;

import com.daniel.task.entities.Task;
import com.daniel.task.exceptions.TaskException;
import com.daniel.task.jsons.CreateTaskRest;
import com.daniel.task.jsons.TaskRest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface TaskService {

    public List<TaskRest> getAll() throws TaskException;

    TaskRest getTaskById(Long id) throws TaskException;

    Task create(CreateTaskRest CreateTaskRest) throws TaskException;

    public Long deleteById(Long id) throws TaskException;

    public Task update(TaskRest taskRest) throws TaskException;

}


