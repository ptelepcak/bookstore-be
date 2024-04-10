package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findByTitle(String title, Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    Book createBook(Book book);
    Book updateBook(String id, Book book);
    void deleteBook(String id);
}
