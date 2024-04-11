package com.example.bookstore.service;

import com.example.bookstore.controller.command.BookCommand;
import com.example.bookstore.controller.dto.BookDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<BookDTO> findByTitle(String title, Pageable pageable);
    Page<BookDTO> findByAuthor(String author, Pageable pageable);
    Page<BookDTO> findByGenre(String genre, Pageable pageable);
    Page<BookDTO> findByTitleOrAuthorOrGenre(String query, Pageable pageable);
    BookDTO createBook(BookCommand bookCommand);
    BookDTO updateBook(String id, BookCommand bookCommand);
    void deleteBook(String id);
}
