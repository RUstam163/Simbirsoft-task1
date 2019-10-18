package com.example.task1.controller;

import com.example.task1.model.Book;
import com.example.task1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    private BookService bookService;

    @Autowired
    public PageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String showRegPage() {
        return "registration";
    }

    @GetMapping(value = "/")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "index";
    }

    @GetMapping(value = "/books")
    public String showCreateForm() {
        return "add-page-form";
    }

    @GetMapping("/books/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "edit-page-form";
    }
}
