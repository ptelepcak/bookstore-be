package com.example.bookstore.service;

import com.example.bookstore.controller.command.InventoryCommand;

public interface InventoryService {
    void createInventory(InventoryCommand inventoryCommand);
}
