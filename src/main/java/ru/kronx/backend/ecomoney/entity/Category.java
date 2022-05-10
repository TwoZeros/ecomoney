package ru.kronx.backend.ecomoney.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "operation_category", schema = "money", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "person_id", nullable = true, updatable = false)
    private Long personId;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "operation_type_id", nullable = false)
    private Short operationTypeId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return id.equals(that.id) && personId.equals(that.personId) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, name);
    }
}
