package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' WHERE id = ?")
@Where(clause = "status = 'Active'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 100, nullable = false)
    @NotNull(message = "Name is required")
    @Length(min=5, max=100)
    private String name;
    @Column(length = 10, nullable = false)
    @NotNull(message = "Category is required")
    @Pattern(regexp = "Back-end|Front-end")
    @Length(max=10)
    private String category;

    @Column(length = 10, nullable = false)
    @NotNull(message = "Category is required")
    @Pattern(regexp = "Active|Inactive")
    @Length(max=10)
    private String status = "Active";
}
