package com.example.baitap2.service;

import com.example.baitap2.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    String borrowBook(long bookId);
    void returnBook(String borrowCode);
    void saveBook(Book book);
}
