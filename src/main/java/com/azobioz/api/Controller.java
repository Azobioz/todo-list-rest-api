package com.azobioz.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Controller {

    private TaskService taskService;

    public Controller(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Integer> createTask(@Valid @RequestBody Task task) {
        taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task.getId());
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(@RequestParam(name = "completed", required = false) Boolean completed) {
        if (completed == null) {
            return taskService.getAllTasks();
        } else if (completed) {
            return taskService.getCompletedTasks();
        }
            return taskService.getNotCompletedTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return taskService.getTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable Integer id, @RequestBody TaskUpdateDTO updateDTO) {
        taskService.updateTask(id, updateDTO);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable("id") Integer id) {
        taskService.deleteTaskById(id);
    }

}
