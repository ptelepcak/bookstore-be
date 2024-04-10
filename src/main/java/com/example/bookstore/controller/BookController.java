package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping("/{query}")
    public Page<Book> getByTitleOrAuthorOrGenre(@PathVariable String query, @PageableDefault Pageable pageableRequest){
        return bookService.findByTitleOrAuthorOrGenre(query, pageableRequest);
    }

    @GetMapping("/author/{author}")
    public Page<Book> getByAuthor(@PathVariable String author, @PageableDefault Pageable pageableRequest){
        return bookService.findByAuthor(author, pageableRequest);
    }

    @GetMapping("/title/{title}")
    public Page<Book> getByTitle(@PathVariable String title, @PageableDefault Pageable pageableRequest){
        return bookService.findByTitle(title, pageableRequest);
    }

    @GetMapping("/genre/{genre}")
    public Page<Book> getByGenre(@PathVariable String genre, @PageableDefault Pageable pageableRequest){
        return bookService.findByGenre(genre, pageableRequest);
    }

    @PutMapping("/{id}")
    public Book updateBook(String id, Book book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(String id){
        bookService.deleteBook(id);
    }
}
