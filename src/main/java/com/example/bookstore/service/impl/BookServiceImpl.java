package com.example.bookstore.service.impl;

import com.example.bookstore.controller.command.BookCommand;
import com.example.bookstore.controller.command.mapper.BookCommandMapper;
import com.example.bookstore.controller.dto.BookDTO;
import com.example.bookstore.controller.dto.mapper.BookMapper;
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
    private final BookMapper dtoMapper;
    private final BookCommandMapper commandMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper dtoMapper, BookCommandMapper commandMapper) {
        this.bookRepository = bookRepository;
        this.dtoMapper = dtoMapper;
        this.commandMapper = commandMapper;
    }

    @Override
    public Page<BookDTO> findByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitle(title, pageable).map(dtoMapper::map);
    }

    @Override
    public Page<BookDTO> findByAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthor(author, pageable).map(dtoMapper::map);
    }

    @Override
    public Page<BookDTO> findByGenre(String genre, Pageable pageable) {
        return bookRepository.findByGenre(genre, pageable).map(dtoMapper::map);
    }

    @Override
    public Page<BookDTO> findByTitleOrAuthorOrGenre(String query, Pageable pageable) {
        return bookRepository.findByTitleOrAuthorOrGenre(query, pageable).map(dtoMapper::map);
    }

    @Override
    public BookDTO createBook(BookCommand bookCommand) {
        Book book = commandMapper.map(bookCommand);
        return dtoMapper.map(bookRepository.save(book));
    }

    @Override
    public BookDTO updateBook(String id, BookCommand bookCommand) {
        Book book = commandMapper.map(bookCommand);
        book.setId(id);
        return dtoMapper.map(bookRepository.save(book));
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
