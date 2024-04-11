package com.example.bookstore.controller.command.mapper;

import com.example.bookstore.common.Mapper;
import com.example.bookstore.controller.command.InventoryCommand;
import com.example.bookstore.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryCommandMapper implements Mapper<InventoryCommand, Inventory> {
    @Override
    public Inventory map(InventoryCommand source) {
        Inventory inventory = new Inventory();
        inventory.setBookId(source.getBookId());
        inventory.setAmount(source.getAmount());
        inventory.setActionType(source.getActionType());
        return inventory;
    }
}
