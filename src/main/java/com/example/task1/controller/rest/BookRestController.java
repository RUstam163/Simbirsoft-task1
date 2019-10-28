package com.example.task1.controller.rest;

import com.example.task1.model.Book;
import com.example.task1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView addBook(@Valid Book book, BindingResult bindingResult,RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            redirAttrs.addFlashAttribute(book);
            return new RedirectView("/books");
        }
        bookService.addBook(book);
        return new RedirectView("/");
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
