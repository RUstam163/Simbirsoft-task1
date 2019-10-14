package com.example.task1.controller;

import com.example.task1.model.Book;
import com.example.task1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/")
    public String showAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "index";
    }

    @GetMapping(value = "/create")
    public String showCreateForm() {
        return "addPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        bookService.delete(id);
        model.addAttribute("books", bookService.getAll());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "editPage";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editPage";
        }
        bookService.editBook(book);
        model.addAttribute("books", bookService.getAll());
        return "redirect:/";
    }

}
