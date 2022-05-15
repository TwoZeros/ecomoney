package ru.kronx.backend.ecomoney.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.backend.ecomoney.entity.Category;
import ru.kronx.backend.ecomoney.model.CategorySearch;
import ru.kronx.backend.ecomoney.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ResponseEntity findById(@RequestParam(value = "id", defaultValue = "null") String id) {
        try {
            return ResponseEntity.ok(categoryService.findById(Long.parseLong(id)));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/all")
    public ResponseEntity<List<Category>> findByUserId(@RequestBody String userId) {
        try {
            System.out.println(userId);
            List<Category> categories = categoryService.findByUserId(Long.parseLong(userId));
            return !categories.isEmpty() ? ResponseEntity.ok(categories) : new ResponseEntity("Нет данных по userId = " + userId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Некоректный userId", HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/search")
    public ResponseEntity<List<Category>> findByTitle(@RequestBody CategorySearch model) {
        if (model.getUserId() == null || model.getUserId() == 0) {
            return new ResponseEntity("Некоректный userId", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            return ResponseEntity.ok(categoryService.findyByTitle(model));
        }
        catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity("Ничего не найдено", HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/new")
    public ResponseEntity createCategory(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity<String>("Парамаетр id генеририруется автоматически", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!categoryService.isCategoryNameNotNull(category)) {
            return new ResponseEntity<String>("Укажите название категории", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getPersonId() == null || category.getPersonId() == 0) {
            return new ResponseEntity<String>("Укажите personId", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody Category category) {
        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity<String>("Парамаетр id обязателен для заполнения", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!categoryService.isCategoryNameNotNull(category)) {
            return new ResponseEntity<String>("Укажите название категории", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getPersonId() == null || category.getPersonId() == 0) {
            return new ResponseEntity<String>("Укажите personId", HttpStatus.NOT_ACCEPTABLE);
        }
        categoryService.updateCategory(category);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return new ResponseEntity<String>("Некорректный id", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            categoryService.deleteCategory(id);
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>("Удаление не произошло, Нет  id =" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
