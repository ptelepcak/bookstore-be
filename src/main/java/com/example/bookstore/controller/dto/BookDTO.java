package com.example.bookstore.controller.dto;

import java.math.BigDecimal;

public record BookDTO(String id, String title, String author, String genre, BigDecimal price) {
}
