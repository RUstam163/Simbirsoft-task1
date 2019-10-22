package com.example.task1.service.impl;

import com.example.task1.model.Book;
import com.example.task1.repository.BookRepository;
import com.example.task1.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService = new BookServiceImpl(bookRepository);

    @Before
    public void setUp() {
        Mockito.when(bookRepository.findAll()).thenReturn(Stream.of(
                new Book(1L, "testBook1", "testAuthor1", 1991),
                new Book(2L, "testBook2", "testAuthor2", 1992))
                .collect(Collectors.toList()));
        Mockito.when(bookRepository.getOne(1L)).thenReturn(
                new Book(1L, "testBook1", "testAuthor1", 1991));
        Mockito.when(bookRepository.findByName("testBook1")).thenReturn(
                new Book(1L, "testBook1", "testAuthor1", 1991));
        Mockito.when(bookRepository.saveAndFlush(Mockito.any(Book.class))).thenReturn(new Book(1L, "testBook1", "testAuthor1", 1991));

    }

    @Test
    public void addBookTest() {
        Book bookTest = new Book();
        Book book = bookService.addBook(bookTest);

        Assert.assertEquals(1, book.getId().intValue());
    }

    @Test
    public void deleteTest() {
        Book book = bookService.getById(1L);
        System.out.println(book);
        bookService.delete(book.getId());
        verify(bookRepository, Mockito.times(1)).deleteById(book.getId());

    }

    @Test
    public void getByNameTest() {
        Book bookTest = bookService.getByName("testBook1");

        Assert.assertEquals("testBook1", bookTest.getName());
    }

    @Test
    public void editBookTest() {
        Book bookTest = bookService.getById(1L);
        System.out.println(bookTest);
        Mockito.when(bookRepository.saveAndFlush(Mockito.any(Book.class))).thenReturn(new Book(555L, "editTestBook", "editTestAuthor", 2019));
        Book book = bookService.editBook(bookTest);
        System.out.println(book);
        Mockito.verify(bookRepository, Mockito.times(1)).saveAndFlush(bookTest);
    }

    @Test
    public void getAllTest() {
        Assert.assertEquals(2, bookService.getAll().size());
    }

    @Test
    public void getByIdTest() {
        Book bookTest = bookService.getById(1L);

        Assert.assertEquals("testBook1", bookTest.getName());
    }
}