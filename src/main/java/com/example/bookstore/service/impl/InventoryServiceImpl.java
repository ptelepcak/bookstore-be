package com.example.bookstore.service.impl;

import com.example.bookstore.controller.command.InventoryCommand;
import com.example.bookstore.controller.command.mapper.InventoryCommandMapper;
import com.example.bookstore.repository.InventoryRepository;
import com.example.bookstore.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryCommandMapper inventoryCommandMapper;
    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryCommandMapper inventoryCommandMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryCommandMapper = inventoryCommandMapper;
    }

    @Override
    public void createInventory(InventoryCommand inventoryCommand) {
        inventoryRepository.save(inventoryCommandMapper.map(inventoryCommand));
    }
}
