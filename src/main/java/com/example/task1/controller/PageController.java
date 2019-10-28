package com.example.task1.controller;

import com.example.task1.model.Book;
import com.example.task1.model.User;
import com.example.task1.service.BookService;
import com.example.task1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Locale;

@Controller
public class PageController {

    private BookService bookService;
    private UserService userService;
    private MessageSource messageSource;


    @Autowired
    public PageController(BookService bookService, UserService userService, MessageSource messageSource) {
        this.bookService = bookService;
        this.userService = userService;
        this.messageSource = messageSource;
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
        User userFromDb = userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(userFromDb);
        model.addAttribute("books", bookService.getAll(userFromDb.getAge()));
        return "index";
    }

    @GetMapping(value = "/books")
    public String showCreateForm(Book book) {
        return "add-page-form";
    }

    @GetMapping("/books/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "edit-page-form";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User userFromDb = userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("message", messageSource.getMessage("access.denied", new String[]{userFromDb.getLogin()}, Locale.getDefault()));
        }
        return "403Page";
    }
}
