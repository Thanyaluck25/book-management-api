package com.bookmanagement.service;

import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);  // Save the book as it is (publishedDate is a String)
    }
}
