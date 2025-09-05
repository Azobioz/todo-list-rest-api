package com.azobioz.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
     void deleteTaskById(Integer id);
     List<Task> getTasksByCompleted(Boolean completed);
     Optional<Task> getTaskById(Integer id);
}
