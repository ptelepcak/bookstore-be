package com.example.bookstore.controller.command.mapper;

import com.example.bookstore.controller.command.BookCommand;
import com.example.bookstore.model.Book;
import com.example.bookstore.common.Mapper;
import org.springframework.stereotype.Component;

@Component
public class BookCommandMapper implements Mapper<BookCommand, Book> {
    @Override
    public Book map(BookCommand source) {
        Book book = new Book();
        book.setTitle(source.getTitle());
        book.setAuthor(source.getAuthor());
        book.setGenre(source.getGenre());
        book.setPrice(source.getPrice());
        return book;
    }
}
