package org.educastur.samuelepv59.todo_list.dtos;

import org.educastur.samuelepv59.todo_list.models.Tag;

public record TagResponse(Long id, String text) {
    public static TagResponse of(Tag tag) {
        return new TagResponse(tag.getId(), tag.getText());
    }
}