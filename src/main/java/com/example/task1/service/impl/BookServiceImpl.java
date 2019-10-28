package com.example.task1.service.impl;

import com.example.task1.model.Book;
import com.example.task1.model.constants.BookDefaultConstants;
import com.example.task1.repository.BookRepository;
import com.example.task1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Book editBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> getAll(Integer age) {
        if (age<18) {
            return bookRepository.findAll().stream().map(book -> book.censored() ? book : BookDefaultConstants.DEFAULT_BOOK).collect(Collectors.toList());
        }
        return bookRepository.findAll();
    }
}
