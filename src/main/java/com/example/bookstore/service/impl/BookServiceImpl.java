package com.example.bookstore.service.impl;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitle(title, pageable);
    }

    @Override
    public Page<Book> findByAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthor(author, pageable);
    }

    @Override
    public Page<Book> findByGenre(String genre, Pageable pageable) {
        return bookRepository.findByGenre(genre, pageable);
    }

    @Override
    public Page<Book> findByTitleOrAuthorOrGenre(String query, Pageable pageable) {
        return bookRepository.findByTitleOrAuthorOrGenre(query, pageable);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String id, Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
