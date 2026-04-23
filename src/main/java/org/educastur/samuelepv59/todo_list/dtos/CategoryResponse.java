package org.educastur.samuelepv59.todo_list.dtos;

import org.educastur.samuelepv59.todo_list.models.Category;

public record CategoryResponse(Long id, String tittle) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(category.getId(), category.getTitle());
    }
}
