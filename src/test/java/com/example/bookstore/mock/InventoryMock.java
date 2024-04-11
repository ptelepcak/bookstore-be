package com.example.bookstore.mock;

import com.example.bookstore.controller.command.InventoryCommand;
import com.example.bookstore.model.enums.ActionType;

public final class InventoryMock {
    public static InventoryCommand mockInventoryCommand() {
        return new InventoryCommand("1", 1000, ActionType.OUTGOING);
    }
}
