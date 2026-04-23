package org.educastur.samuelepv59.todo_list.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Data: Genera Getters, Setters, toString, equals y hashCode.
 *        Ahorra código repetitivo (Boilerplate).
 */
@Data

/**
 * @NoArgsConstructor: Constructor sin argumentos. JPA lo necesita para
 *                     crear la instancia antes de rellenarla con datos de la
 *                     BD.
 */
@NoArgsConstructor

/**
 * @AllArgsConstructor: Constructor con todos los campos.
 *                      Es obligatorio si usas @Builder.
 */
@AllArgsConstructor

/**
 * @Builder: Permite crear objetos de forma fluida:
 *           User.builder().name("X").build().
 *           Alternativa: Usar constructores manuales o Setters tradicionales.
 */
@Builder

/**
 * @Entity: Marca esta clase como una tabla de base de datos.
 */
@Entity

/**
 * @Table: Define el nombre de la tabla.
 *         Usamos "user_entity" porque "USER" es palabra reservada en
 *         H2/Postgres.
 *         Opción: Podrías usar @Table(name = "usuarios_sistema").
 */
@Table(name = "user_entity")
public class User implements UserDetails {

    /**
     * @Id: Define la clave primaria.
     * @GeneratedValue: El motor (H2) genera el número solo (1, 2, 3...).
     *                  Otras opciones: GenerationType.UUID si prefieres IDs tipo
     *                  "abc-123-efg".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column(unique = true): Evita que dos personas tengan el mismo username.
     */
    @Column(unique = true, nullable = false)
    private String username;

    private String email;

    /**
     * En producción, esta password SIEMPRE debe guardarse encriptada (BCrypt).
     */
    private String password;

    /**
     * @Builder.Default: Evita que al usar el Builder, este campo sea null.
     *                   Aquí inicializamos a false por defecto.
     */
    @Builder.Default
    private boolean isAdmin = false;

    // --- MÉTODOS DE USERDETAILS (Spring Security) ---

    /**
     * getAuthorities: Define los "permisos" del usuario.
     * Spring Security espera que los roles empiecen por "ROLE_".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = isAdmin ? "ROLE_ADMIN" : "ROLE_USER";
        return List.of(new SimpleGrantedAuthority(role));
    }

    /**
     * Estos 4 métodos suelen devolverse como 'true' en desarrollos rápidos.
     * Si quisieras bloquear a alguien, cambiarías 'isEnabled' a false.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}