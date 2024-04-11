package com.example.bookstore.mock;

import com.example.bookstore.controller.command.BookCommand;
import com.example.bookstore.controller.dto.BookDTO;
import com.example.bookstore.model.Book;

import java.math.BigDecimal;

public final class BookMock {
    public static Book mockBook(){
        Book book = new Book();
        book.setId("1");
        book.setAuthor("Author Authorson");
        book.setTitle("Book Title");
        book.setGenre("Biography");
        book.setPrice(new BigDecimal(100));
        return book;
    }

    public static BookCommand mockBookCommand(){
        return new BookCommand("Book Title","Author Authorson", "Biography", new BigDecimal(100));
    }

    public static BookDTO mockBookDTO() {
        return new BookDTO("1", "Book Title", "Author Authorson", "Biography", new BigDecimal(100));
    }
}
