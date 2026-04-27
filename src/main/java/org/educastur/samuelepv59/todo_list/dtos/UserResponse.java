package org.educastur.samuelepv59.todo_list.dtos;

import org.educastur.samuelepv59.todo_list.models.User;
import org.educastur.samuelepv59.todo_list.models.UserRole;

/**
 * Usamos record porque es una clase de "solo lectura" para el frontend.
 * Java genera automáticamente getters, constructor, equals y hashCode.
 */
public record UserResponse(
        Long id,
        String username,
        String email,
        UserRole Rol) {
    /**
     * Método estático (Mapper) para transformar la entidad User en este Record.
     * Así, el controlador solo tiene que hacer: UserResponse.of(usuario);
     */
    public static UserResponse of(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRol());
    }
}
