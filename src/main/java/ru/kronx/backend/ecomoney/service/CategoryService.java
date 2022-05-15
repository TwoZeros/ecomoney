package ru.kronx.backend.ecomoney.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.kronx.backend.ecomoney.entity.Category;
import ru.kronx.backend.ecomoney.model.CategorySearch;
import ru.kronx.backend.ecomoney.repository.OperationCategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public Category updateCategory(Category category) {
        return repository.save(category);
    }
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
    public boolean isCategoryNameNotNull(Category category) {
        return category.getName() != null || category.getName().trim().length() != 0;
    }
    public List<Category> findyByTitle(CategorySearch model) {
        String title = model.getTitle();
        List<Category> catatogryList = title == null || title.isBlank() ?
                repository.findCategoryByPersonIdOrderByName(model.getUserId()) :
                repository.findByTitle(model.getUserId(), model.getTitle());
        if (catatogryList.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (title == null || title.isBlank()) {
            return catatogryList;
        }

        List<Category> catStartWithSearchTitle =  catatogryList.stream()
                .filter(x -> x.getName().toLowerCase().startsWith(title.toLowerCase()))
                .collect(Collectors.toList());
        return catStartWithSearchTitle.size() == 0 ? catatogryList : catStartWithSearchTitle;
    }


}
