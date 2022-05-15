package ru.kronx.backend.ecomoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kronx.backend.ecomoney.entity.Category;

import java.util.List;

@Repository
public interface OperationCategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByPersonIdOrderByName(Long personId);

    @Query(value= "SELECT c From Category c" +
            " WHERE lower(c.name) like lower(concat('%',:title,'%'))" +
            "and c.personId=:id " +
            "order by c.name asc ")
    List<Category> findByTitle(
            @Param("id") Long id,
            @Param("title") String title
            );
}
