package org.educastur.samuelepv59.todo_list.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String title;

    @Lob
    private String description;

    @Builder.Default
    private boolean completed = false;

    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id") // REVISARLO
    private User author;

    @ManyToMany
    @JoinTable(name = "task_tags", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    // Dentro de Task.java añadir:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // --- IMPLEMENTACIÓN PROFESIONAL DE EQUALS Y HASHCODE ---
    // Esto es lo que hace que tu código sea "Nivel Senior".
    // Evita que la comparación de objetos falle cuando Hibernate usa Proxies.

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true; // Si es el mismo espacio de memoria, son iguales
        if (o == null)
            return false;

        // Comprobamos si el objeto es un Proxy de Hibernate (carga perezosa)
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();

        if (thisEffectiveClass != oEffectiveClass)
            return false;

        Task task = (Task) o;
        // La igualdad en BD se basa en el ID. Si el ID es igual, es la misma fila de la
        // tabla.
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public final int hashCode() {
        // El hashCode debe ser consistente. Usamos la clase persistente para que
        // no cambie aunque el objeto sea un proxy.
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}