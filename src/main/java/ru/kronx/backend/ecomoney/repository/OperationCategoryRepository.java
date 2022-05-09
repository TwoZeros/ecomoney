package ru.kronx.backend.ecomoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kronx.backend.ecomoney.entity.Category;

import java.util.List;

@Repository
public interface OperationCategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByPersonIdOrderByName(Long personId);
}
