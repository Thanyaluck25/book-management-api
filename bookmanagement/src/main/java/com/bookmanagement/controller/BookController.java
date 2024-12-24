package com.bookmanagement.controller;

import com.bookmanagement.model.Book;
import com.bookmanagement.service.BookService;
import com.bookmanagement.util.DateConverter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Get books by author
    @GetMapping
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    // Save a book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@Validated @RequestBody Book book, BindingResult result) {
        if (book.getPublishedDate() != null && !book.getPublishedDate().isEmpty()) {
            book.setPublishedDate(DateConverter.convertBuddhistToGregorian(book.getPublishedDate()));
        }

        if (result.hasErrors()) {
            throw new RuntimeException("Invalid book data: " + result.getAllErrors());
        }
        return bookService.saveBook(book);
    }
}
