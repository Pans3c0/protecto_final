package org.educastur.samuelepv59.todo_list.repositories;

import org.educastur.samuelepv59.todo_list.models.Task;
import org.educastur.samuelepv59.todo_list.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAuthor(User author);

    List<Task> findByTitleContaining(String title);
}