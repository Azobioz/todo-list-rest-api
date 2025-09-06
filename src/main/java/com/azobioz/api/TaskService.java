package com.azobioz.api;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void saveTask(Task task) {
        repository.save(task);
    }

    @Transactional
    public void deleteTaskById(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        repository.deleteTaskById(id);
    }

    public List<Task> getCompletedTasks() {
        return repository.getTasksByCompleted(true);
    }

    public List<Task> getNotCompletedTasks() {
        return repository.getTasksByCompleted(false);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTaskById(Integer id) {
        return repository.getTaskById(id);
    }

    @Transactional
    public void updateTask(Integer id, TaskUpdateDTO updateDTO) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        if (updateDTO.getTitle() != null) {
            task.setTitle(updateDTO.getTitle());
        }
        if (updateDTO.getDescription() != null) {
            task.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getCompleted() != null) {
            task.setCompleted(updateDTO.getCompleted());
        }

        repository.save(task);
    }
}
