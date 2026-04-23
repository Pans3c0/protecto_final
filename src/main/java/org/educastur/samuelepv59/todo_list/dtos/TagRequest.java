package org.educastur.samuelepv59.todo_list.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TagRequest {
    @NotBlank(message = "El nombre del tag es obligatorio")
    private String title;
}
