package com.example.bookstore.controller.command;

import com.example.bookstore.model.enums.ActionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryCommand {
    private final @NotBlank(message = "Book ID required") String bookId;
    private final @PositiveOrZero(message = "Amount cannot be negative number") Integer amount;
    private final @NotNull(message = "Action type required") ActionType actionType;
}
