package org.educastur.samuelepv59.todo_list.services;

import lombok.RequiredArgsConstructor;
import org.educastur.samuelepv59.todo_list.dtos.TaskRequest;
import org.educastur.samuelepv59.todo_list.dtos.TaskResponse;
import org.educastur.samuelepv59.todo_list.models.Task;
import org.educastur.samuelepv59.todo_list.models.User;
import org.educastur.samuelepv59.todo_list.repositories.CategoryRepository;
import org.educastur.samuelepv59.todo_list.repositories.TaskRepository;
import org.educastur.samuelepv59.todo_list.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // Listar tareas de un usuario concreto
    public List<TaskResponse> getTasksByAuthor(Long authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return taskRepository.findById(authorId)
                .stream()
                .map(TaskResponse::of)
                .toList();
    }

    // Crear tarea vinculando el ID del autor que viene del Front
    public TaskResponse create(TaskRequest request, Long authorId) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .completed(false)
                // Buscamos las entidades reales por sus IDs
                .author(userRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Usuario no encontrado")))
                .category(categoryRepository.findById(request.getCategoryId()).orElse(null))
                .build();

        return TaskResponse.of(taskRepository.save(task));
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}