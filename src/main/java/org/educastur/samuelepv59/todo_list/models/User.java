package org.educastur.samuelepv59.todo_list.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad de Usuario simplificada.
 * Hemos eliminado 'implements UserDetails' porque ya no usamos Spring Security.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String email;

    // Ahora guardaremos la contraseña en texto plano para tus pruebas locales
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole rol = UserRole.USER;
    // Nota: He cambiado 'Rol' por 'role' (minúscula) que es la convención en Java
}