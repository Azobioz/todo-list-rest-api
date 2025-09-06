package com.azobioz.api;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "title is required")
    private String title;
    @Column(nullable = true)
    private String description;
    @NotNull(message = "status is required")
    private Boolean completed;
}
