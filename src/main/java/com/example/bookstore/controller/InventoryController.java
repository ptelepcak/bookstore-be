package com.example.bookstore.controller;

import com.example.bookstore.controller.command.InventoryCommand;
import com.example.bookstore.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    @Operation(summary = "Add inventory")
    public void createInventory(@Valid @RequestBody InventoryCommand inventoryCommand){
        inventoryService.createInventory(inventoryCommand);
    }
}
