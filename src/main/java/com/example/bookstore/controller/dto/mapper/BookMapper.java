package com.example.bookstore.controller.dto.mapper;

import com.example.bookstore.controller.dto.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.common.Mapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDTO> {

    @Override
    public BookDTO map(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPrice());
    }
}
