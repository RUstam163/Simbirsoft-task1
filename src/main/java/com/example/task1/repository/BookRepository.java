package com.example.task1.repository;

import com.example.task1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);
}
