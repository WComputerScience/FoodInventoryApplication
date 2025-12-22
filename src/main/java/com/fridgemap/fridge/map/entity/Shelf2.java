package com.fridgemap.fridge.map.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Shelf2 {
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

    public Shelf2(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }
}
