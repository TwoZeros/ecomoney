package ru.kronx.backend.ecomoney.service;

import org.springframework.stereotype.Service;
import ru.kronx.backend.ecomoney.entity.Category;
import ru.kronx.backend.ecomoney.repository.OperationCategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final OperationCategoryRepository repository;

    public CategoryService(OperationCategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Category> findByUserId(Long userId) {
        return repository.findCategoryByPersonIdOrderByName(userId);
    }
    public Category createCategory(Category category) {
        return repository.save(category);
    }
}
