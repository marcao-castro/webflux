package com.apirest.webflux.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@With
@Table("person")
public class Person {

    @Id
    private Integer id;

    @NotNull
    @NotEmpty(message = "The name can not be empty")
    private String name;
}
