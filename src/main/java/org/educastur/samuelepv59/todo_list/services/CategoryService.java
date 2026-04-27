package org.educastur.samuelepv59.todo_list.services;


import lombok.RequiredArgsConstructor;
import org.educastur.samuelepv59.todo_list.models.Category;
import org.educastur.samuelepv59.todo_list.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Category save(Category category){
        return categoryRepository.save(category);
    }
    public void delete (Long id){
        categoryRepository.deleteById(id);
    }
    // Pasamos el objeto que queremos editar y su nuevo titulo
    public void edit (Category category, String titulo){
        categoryRepository.findById(category.getId())
                .get()
                .setTitle(titulo);
    }
}
