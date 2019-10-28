package com.example.task1.model;

import javax.persistence.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "book.not.null.name")
    @Size(min = 3, max = 30)
    @Column(name = "name")
    private String name;

    @NotNull(message = "book.not.null.author")
    @Size(min = 5, max = 30)
    @Column(name = "author")
    private String author;

    @NotNull(message = "book.not.null.year")
    @Digits(integer = 4, fraction = 0, message = "Enter only the year")
    @Column(name = "year_create")
    private int year;

    @NotNull
    @Column(name = "is_censored")
    private boolean censored;

    public Book() {
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(String name, String author, int year, boolean censored) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.censored = censored;
    }

    public Book(Long id, String name, String author, int year, boolean censored) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.censored = censored;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean censored() {
        return censored;
    }

    public void setCensored(boolean censored) {
        this.censored = censored;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", isCensored=" + censored +
                '}';
    }
}
