package com.example.bookstore.model;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "book")
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private BigDecimal price;

}
