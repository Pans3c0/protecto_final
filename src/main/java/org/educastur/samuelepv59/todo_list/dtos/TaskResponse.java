package org.educastur.samuelepv59.todo_list.dtos;

import org.educastur.samuelepv59.todo_list.models.Task;
import java.time.LocalDateTime;
import java.util.List;

public record TaskResponse(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDateTime createdAt, // Añadido para que el front sepa cuándo se creó
        LocalDateTime deadline, // Añadido para mostrar la fecha límite
        CategoryResponse category, // Cambiado de String a su DTO
        List<TagResponse> tags, // Añadido: lista de DTOs de etiquetas
        UserResponse author // Cambiado de String a su DTO
) {
    public static TaskResponse of(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt(),
                task.getDeadline(),
                // Si la categoría es nula, pasamos null, si no, su DTO
                task.getCategory() != null ? CategoryResponse.of(task.getCategory()) : null,
                // Mapeamos el Set de Tags de la entidad a una Lista de DTOs
                task.getTags().stream()
                        .map(TagResponse::of)
                        .toList(),
                // Mapeamos el autor a su DTO
                UserResponse.of(task.getAuthor()));
    }
}