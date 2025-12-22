package com.fridgemap.fridge.map.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.DataAmount;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cheese {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Size(min=2, max=25, message="You must enter a name.")
    @Column(name = "type")
    @NotBlank(message = "Please enter at least two characters for the name field.")
    private String type;


    @Min(value = 1, message = "You need to add at least 1")
    @Max(value = 100, message = "That's a few too many.")
    @Digits(integer=2, fraction=0, message="You must enter an integer.")
    @Positive(message = "Number must be positive.")
    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    public Cheese(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }
}
