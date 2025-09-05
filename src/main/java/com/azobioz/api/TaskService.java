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

    public void updateTask(Integer id, Task updatedTask) {
        Task task = repository.getTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        if (updatedTask.getTitle() != null) {
            task.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            task.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getCompleted() != task.getCompleted()) {
            task.setCompleted(updatedTask.getCompleted());
        }
        repository.save(task);
    }
}
