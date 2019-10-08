package com.example.task1.controller;

import com.example.task1.model.Book;
import com.example.task1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        System.out.println(book);
        bookService.addBook(book);
        return new ResponseEntity<>("книга добавлена", HttpStatus.OK);
    }

}
