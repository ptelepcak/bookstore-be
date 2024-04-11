package com.example.bookstore.service;

import com.example.bookstore.controller.command.BookCommand;
import com.example.bookstore.controller.command.mapper.BookCommandMapper;
import com.example.bookstore.controller.dto.BookDTO;
import com.example.bookstore.controller.dto.mapper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import static com.example.bookstore.mock.BookMock.mockBook;
import static com.example.bookstore.mock.BookMock.mockBookCommand;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    private final BookMapper bookMapper = new BookMapper();
    private final BookCommandMapper bookCommandMapper = new BookCommandMapper();

    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(bookRepository, bookMapper, bookCommandMapper);
    }



    @Test
    void testFindByTitle() {
        String title = "Book Title";
        Pageable pageable = mock(Pageable.class);
        Page<Book> expectedPage = mock(Page.class);
        when(bookRepository.findByTitle(title, pageable)).thenReturn(expectedPage);

        Page<BookDTO> result = bookService.findByTitle(title, pageable);

        verify(bookRepository, times(1)).findByTitle(title, pageable);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void testCreateBook() {
        Book book = mockBook();
        BookCommand bookCommand = mockBookCommand();
        when(bookRepository.save(any())).thenReturn(book);

        BookDTO result = bookService.createBook(bookCommand);

        book.setId("1");
        Assertions.assertEquals(book.getId(), result.id());
        Assertions.assertEquals(book.getAuthor(), result.author());
        Assertions.assertEquals(book.getTitle(), result.title());
        Assertions.assertEquals(book.getGenre(), result.genre());
        Assertions.assertEquals(book.getPrice(), result.price());
    }

    @Test
    void testUpdateBook() {
        Book book = mockBook();
        BookCommand bookCommand = mockBookCommand();
        String id = book.getId();
        when(bookRepository.save(any())).thenReturn(book);

        BookDTO result = bookService.updateBook(id, bookCommand);

        Assertions.assertEquals(id, result.id());
        Assertions.assertEquals(book.getAuthor(), result.author());
        Assertions.assertEquals(book.getTitle(), result.title());
        Assertions.assertEquals(book.getGenre(), result.genre());
        Assertions.assertEquals(book.getPrice(), result.price());
        verify(bookRepository, times(1)).save(book);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void testDeleteBook() {
        String id = "1";

        bookService.deleteBook(id);

        verify(bookRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(bookRepository);
    }
}

