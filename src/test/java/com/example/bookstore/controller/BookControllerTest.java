package com.example.bookstore.controller;

import com.example.bookstore.controller.command.BookCommand;
import com.example.bookstore.controller.dto.BookDTO;
import com.example.bookstore.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.example.bookstore.mock.BookMock.mockBookCommand;
import static org.mockito.BDDMockito.given;

import static com.example.bookstore.mock.BookMock.mockBookDTO;
import static java.util.Collections.singletonList;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "USER")
    public void testGetByAuthor() throws Exception {
        String author = "Author Authorson";
        Pageable pageRequest = PageRequest.of(0, 10);
        Page<BookDTO> bookDTOPage = new PageImpl<>(
                singletonList(mockBookDTO()),
                pageRequest,
                1
        );
        given(bookService.findByAuthor(author, pageRequest)).willReturn(bookDTOPage);

        mockMvc.perform(get("/books/author/" + author))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(bookDTOPage))
                );
    }

    @Test
    public void testGetByAuthorUnauthorized() throws Exception {
        String author = "Author Authorson";
        Pageable pageRequest = PageRequest.of(0, 10);
        Page<BookDTO> bookDTOPage = new PageImpl<>(
                singletonList(mockBookDTO()),
                pageRequest,
                1
        );
        given(bookService.findByAuthor(author, pageRequest)).willReturn(bookDTOPage);

        mockMvc.perform(get("/books/author/" + author))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostBook() throws Exception {
        BookCommand bookCommand = mockBookCommand();
        BookDTO bookDTO = mockBookDTO();
        given(bookService.createBook(bookCommand)).willReturn(bookDTO);

        mockMvc.perform(post("/books").content(toJson(bookCommand)).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(bookDTO))
                );
    }

    @Test
    public void testPostBookUnauthorized() throws Exception {
        BookCommand bookCommand = mockBookCommand();
        BookDTO bookDTO = mockBookDTO();
        given(bookService.createBook(bookCommand)).willReturn(bookDTO);

        mockMvc.perform(post("/books").content(toJson(bookCommand)).
                        contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isUnauthorized()
                );
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostBookInputIncomplete() throws Exception {
        BookCommand bookCommand = new BookCommand("Title", null, "Genre", new BigDecimal(100));
        BookDTO bookDTO = mockBookDTO();
        given(bookService.createBook(bookCommand)).willReturn(bookDTO);

        mockMvc.perform(post("/books").content(toJson(bookCommand)).
                        contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Author is required")
                );
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPutBook() throws Exception {
        BookCommand bookCommand = mockBookCommand();
        String id = "1";
        BookDTO bookDTO = mockBookDTO();
        given(bookService.updateBook(id, bookCommand)).willReturn(bookDTO);

        mockMvc.perform(put("/books/" + id).content(toJson(bookCommand)).
                        contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isOk()
                );
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteBook() throws Exception {
        String id = "1";
        mockMvc.perform(delete("/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                        .andExpect(status().isOk()
                );
    }

    @Test
    public void testDeleteBookUnauthorized() throws Exception {
        String id = "1";
        mockMvc.perform(delete("/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isUnauthorized()
                );
    }


    private String toJson(Object data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }
}
