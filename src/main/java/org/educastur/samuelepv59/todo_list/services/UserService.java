package org.educastur.samuelepv59.todo_list.services;

import lombok.RequiredArgsConstructor;
import org.educastur.samuelepv59.todo_list.dtos.CreateUserRequest;
import org.educastur.samuelepv59.todo_list.dtos.LoginRequest;
import org.educastur.samuelepv59.todo_list.dtos.UserResponse;
import org.educastur.samuelepv59.todo_list.models.User;
import org.educastur.samuelepv59.todo_list.models.UserRole;
import org.educastur.samuelepv59.todo_list.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Servicio para la gestión de usuarios.
 * Maneja el registro y la validación de credenciales en texto plano.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Registra un nuevo usuario en el sistema.
     */
    public UserResponse register(CreateUserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // Texto plano
                .rol(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);
        return UserResponse.of(savedUser);
    }

    /**
     * Valida las credenciales de un usuario.
     * @param request Datos de acceso (username y password)
     * @return UserResponse si el login es correcto
     * @throws RuntimeException si las credenciales son inválidas
     */
    public UserResponse login(LoginRequest request) {
        // 1. Buscamos el usuario por su nombre de usuario
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Comparamos la contraseña directamente (texto plano)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // 3. Devolvemos la respuesta si todo coincide
        return UserResponse.of(user);
    }
}