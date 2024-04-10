package com.example.bookstore.common;


public interface Mapper<S, T> {
    T map(S source);
}