package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book, String> {
    Page<Book> findByTitle(String title, Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByGenre(String genre, Pageable pageable);
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"title\", \"author\", \"genre\"]}}")
    Page<Book> findByTitleOrAuthorOrGenre(String query, Pageable pageable);
}
