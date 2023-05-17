package com.daniel.task.controllers;

import com.daniel.task.entities.Task;
import com.daniel.task.exceptions.TaskException;
import com.daniel.task.jsons.CreateTaskRest;
import com.daniel.task.jsons.TaskRest;
import com.daniel.task.responses.TaskResponse;
import com.daniel.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "/TODO" + "/v1")
public class TaskController {

    @Autowired
    TaskService taskService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponse<List<TaskRest>> getTasks() throws TaskException {
        return new TaskResponse<List<TaskRest>>("Success", String.valueOf(HttpStatus.OK), "OK",
                taskService.getAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "task" + "/{" + "taskId" + "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponse<TaskRest> getTaskById(@PathVariable Long taskId) throws TaskException {
        return new TaskResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                taskService.getTaskById(taskId));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "task", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponse<Task> createTask(@RequestBody CreateTaskRest createTaskRest) throws TaskException {
        return new TaskResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                taskService.create(createTaskRest));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponse<Task> updateTask(@RequestBody TaskRest taskRest) throws TaskException {
        System.out.println("CONTROLLER UPDATE: ");
        return new TaskResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                taskService.update(taskRest));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "delete" + "/{" + "taskId" + "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponse<Long> deleteTask(@PathVariable Long taskId) throws TaskException {
        return new TaskResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                taskService.deleteById(taskId));
    }
}
