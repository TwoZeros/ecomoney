package ru.kronx.backend.ecomoney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "operation_journal", schema = "money", catalog = "postgres")
@Getter
@Setter

public class Operation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName ="id")
    private Bill Bill;

    @Basic
    @Column(name = "sum", nullable = false, precision = 2)
    private BigDecimal sum;
    @Basic
    @Column(name = "timestamp", nullable = true)
    private Timestamp timestamp;
    @Basic
    @Column(name = "operation_type_id", nullable = false)
    private Short operationTypeId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation that = (Operation) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
