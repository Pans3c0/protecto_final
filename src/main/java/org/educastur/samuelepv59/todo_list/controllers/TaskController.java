package org.educastur.samuelepv59.todo_list.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.educastur.samuelepv59.todo_list.dtos.TaskRequest;
import org.educastur.samuelepv59.todo_list.dtos.TaskResponse;
import org.educastur.samuelepv59.todo_list.services.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // Obtener tareas: GET /tasks?authorId=1
    @GetMapping
    public List<TaskResponse> getUserTasks(@RequestParam Long authorId) {
        return taskService.getTasksByAuthor(authorId);
    }

    // Crear tarea: POST /tasks?authorId=1
    @PostMapping
    public TaskResponse createTask(
            @Valid @RequestBody TaskRequest request,
            @RequestParam Long authorId) {
        return taskService.create(request, authorId);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }
}