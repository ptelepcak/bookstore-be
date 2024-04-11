package com.example.bookstore.repository;

import com.example.bookstore.model.Inventory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface InventoryRepository extends ElasticsearchRepository<Inventory, String> {
}
