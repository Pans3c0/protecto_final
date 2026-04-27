package org.educastur.samuelepv59.todo_list.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.educastur.samuelepv59.todo_list.dtos.CreateUserRequest;
import org.educastur.samuelepv59.todo_list.dtos.LoginRequest;
import org.educastur.samuelepv59.todo_list.dtos.UserResponse;
import org.educastur.samuelepv59.todo_list.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @RestController indica que esta clase responderá con JSON.
 * @RequestMapping("/auth") define que todas las rutas de esta clase empezarán por /auth.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * Endpoint para el registro de nuevos usuarios.
     * @Valid activa las validaciones de las anotaciones que pusimos en el DTO (@NotBlank, etc).
     * @RequestBody indica que el JSON que envía el cliente debe mapearse a nuestro objeto CreateUserRequest.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody CreateUserRequest request) {
        // Llamamos al servicio y devolvemos la respuesta con un código 201 (Created)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        try {
            UserResponse response = userService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Si el usuario o contraseña fallan, devolvemos un 401 (No autorizado)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}