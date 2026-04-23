package org.educastur.samuelepv59.todo_list.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    // Estos son los campos que el usuario rellena en el formulario de registro
    private String username;
    private String email;
    private String password;
    private String verifyPassword; // Campo extra para validar que no se equivocó
}