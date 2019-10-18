package com.example.task1.controller.rest;

import com.example.task1.model.Book;
import com.example.task1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book book1 = bookService.addBook(book);
        return new ResponseEntity<>(book1, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") long id) {
        bookService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/books/{id}")
    public ResponseEntity updateBook(@PathVariable("id") long id, @Valid @RequestBody Book book, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }
        bookService.editBook(book);
        return new ResponseEntity(HttpStatus.OK);
    }

}
