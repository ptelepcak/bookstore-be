package com.example.bookstore.controller;

import com.example.bookstore.controller.command.BookCommand;
import com.example.bookstore.controller.dto.BookDTO;
import com.example.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    @Operation(summary = "Add new book")
    public BookDTO createBook(@Valid @RequestBody BookCommand bookCommand){
        return bookService.createBook(bookCommand);
    }

    @GetMapping("/{stringQuery}")
    @Operation(summary = "Search for books by title, author or genre")
    public Page<BookDTO> getByTitleOrAuthorOrGenre(@PathVariable String stringQuery, @PageableDefault Pageable pageableRequest){
        return bookService.findByTitleOrAuthorOrGenre(stringQuery, pageableRequest);
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "Find books by author")
    public Page<BookDTO> getByAuthor(@PathVariable String author, @PageableDefault Pageable pageableRequest){
        return bookService.findByAuthor(author, pageableRequest);
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Find books by title")
    public Page<BookDTO> getByTitle(@PathVariable String title, @PageableDefault Pageable pageableRequest){
        return bookService.findByTitle(title, pageableRequest);
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Find books by genre")
    public Page<BookDTO> getByGenre(@PathVariable String genre, @PageableDefault Pageable pageableRequest){
        return bookService.findByGenre(genre, pageableRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modify existing book")
    public BookDTO updateBook(String id, @Valid @RequestBody BookCommand bookCommand){
        return bookService.updateBook(id, bookCommand);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing book")
    public void deleteBook(String id){
        bookService.deleteBook(id);
    }
}
