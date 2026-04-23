package org.educastur.samuelepv59.todo_list.repositories;

import org.educastur.samuelepv59.todo_list.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByText(String text);
}