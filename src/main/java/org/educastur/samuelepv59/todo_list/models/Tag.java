package org.educastur.samuelepv59.todo_list.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String text;

    /**
     * MEJORA 2: Relación con Task.
     * Normalmente, una tarea tiene muchos tags y un tag está en muchas tareas.
     * Esto es un @ManyToMany. Lo dejamos preparado (mappedBy indica que Task
     * manda).
     * Usamos Set en lugar de List para evitar etiquetas duplicadas en una misma
     * tarea.
     */
    @ManyToMany(mappedBy = "tags")
    @Builder.Default
    @ToString.Exclude // Evita bucles infinitos en el método toString()
    private Set<Task> tasks = new HashSet<>();

    // --- MÉTODOS EQUALS Y HASHCODE (Mantenemos tu lógica profesional) ---

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        Tag tag = (Tag) o;
        return getId() != null && Objects.equals(getId(), tag.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}