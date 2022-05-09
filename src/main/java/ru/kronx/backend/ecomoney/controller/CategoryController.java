package ru.kronx.backend.ecomoney.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.backend.ecomoney.entity.Category;
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
    public Category findById(@RequestParam(value = "id", defaultValue = "null") String id) {
        try {
            return  categoryService.findById(Long.parseLong(id));
        }
        catch (Exception e) {
            return null;
        }

    }
    @PostMapping("/all")
    public ResponseEntity<List<Category>> findByUserId(@RequestBody String userId){
        try {
            System.out.println(userId);
            List<Category> categories = categoryService.findByUserId(Long.parseLong(userId));
            return !categories.isEmpty() ? ResponseEntity.ok(categories) : new ResponseEntity("Нет данных по userId = " + userId, HttpStatus.NOT_FOUND) ;
        }
        catch (Exception e) {
            return new ResponseEntity("Некоректный userId", HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/new")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if(category.getId() !=null && category.getId() !=0) {
            return  new ResponseEntity("Парамаетр id генеририруется автоматически", HttpStatus.NOT_ACCEPTABLE);
        }
        if(category.getName() == null || category.getName().trim().length() == 0) {
            return  new ResponseEntity("Укажите название категории", HttpStatus.NOT_ACCEPTABLE);
        }
        if(category.getPersonId() == null || category.getPersonId() == 0) {
            return  new ResponseEntity("Укажите personId", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
}
