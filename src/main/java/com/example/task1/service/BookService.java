package com.example.task1.service;

import com.example.task1.model.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    void delete(long id);

    Book getByName(String name);

    Book editBook(Book book);

    List<Book> getAll();

    Book getById(Long id);
}
