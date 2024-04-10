package com.example.bookstore.controller.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BookCommand {
    private final @NotBlank(message = "Title is required") @Size(max = 100) String title;
    private final @NotBlank(message = "Author is required") @Size(max = 100) String author;
    private final @NotBlank(message = "Genre is required") @Size(max = 25) String genre;
    private final @PositiveOrZero BigDecimal price;



}
