package org.educastur.samuelepv59.todo_list.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 100, message = "Título demasiado largo")
    private String title;

    private String description;

    @Future(message = "La fecha límite debe ser en el futuro")
    private LocalDateTime deadline;

    @NotNull(message = "La tarea debe pertenecer a una categoría")
    private Long categoryId; // El front envía el ID, no el objeto completo
}