package com.example.bookstore.model;

import com.example.bookstore.model.enums.ActionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "inventory")
@Data
public class Inventory {
    @Id
    private String id;
    private String bookId;
    private Integer amount;
    private ActionType actionType;
}
