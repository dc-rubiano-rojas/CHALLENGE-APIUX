package com.daniel.task.services;

import com.daniel.task.entities.Task;
import com.daniel.task.exceptions.InternalServerErrorException;
import com.daniel.task.exceptions.NotFoundException;
import com.daniel.task.exceptions.TaskException;
import com.daniel.task.jsons.CreateTaskRest;
import com.daniel.task.jsons.TaskRest;
import com.daniel.task.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Repository
public class TaskImpl implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskImpl.class);

    @Autowired
    TaskRepository taskRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    public List<TaskRest> getAll() throws TaskException {
        final List<Task> tasksEntity = taskRepository.findAll();
        return tasksEntity.stream().map(service -> modelMapper.map(service, TaskRest.class) ).collect(Collectors.toList());
    }

    public TaskRest getTaskById(Long id) throws TaskException {
        return modelMapper.map(getTaskEntity(id), TaskRest.class);
    }

    private Task getTaskEntity(Long id) throws TaskException {
        return taskRepository
                .findById(id)
                .orElseThrow( () -> new NotFoundException("SNOT-404-1", "TASK_NOT_FOUND"));
    }

    public Long deleteById(Long id) throws TaskException {
        taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TASK_NOT_FOUND", "TASK_NOT_FOUND"));

        try {
            taskRepository.deleteById(id);
        } catch(Exception e) {
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

        return id;
    }

    public Task create(CreateTaskRest createTaskRest) throws TaskException {
        System.out.println("CREATE");

        final Task task = new Task();
        task.setDescription(createTaskRest.getDescription());
        task.setDone(createTaskRest.getDone());
        task.setDate(createTaskRest.getDate());

        try {
            return taskRepository.save(task);
        } catch(final Exception e) {
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public Task update(TaskRest taskRest) throws TaskException {
        Task taskFound = taskRepository.findById(taskRest.getId())
                .orElseThrow(() -> new NotFoundException("TASK_NOT_FOUND", "TASK_NOT_FOUND"));

        System.out.println("UPDATE");
        taskFound.setDate(taskFound.getDate());
        taskFound.setDescription(taskRest.getDescription());
        taskFound.setDone(taskRest.getDone());

        try {
            return taskRepository.save(taskFound);
        } catch(final Exception e) {
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

}
