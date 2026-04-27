package org.educastur.samuelepv59.todo_list.controllers;


import lombok.RequiredArgsConstructor;
import org.educastur.samuelepv59.todo_list.models.Category;
import org.educastur.samuelepv59.todo_list.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.findAll();
    }

    @PostMapping
    public Category create(@RequestBody Category category){
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
}
