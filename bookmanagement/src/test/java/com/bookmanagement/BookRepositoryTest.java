package com.bookmanagement;

import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional  // Ensures that changes to the database are rolled back after each test
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();  // Clear the table before inserting new data

        // Create and save the books
        book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Thanyaluck");
        book1.setPublishedDate("2024-12-24");

        book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor("Yiemyod");
        book2.setPublishedDate("2023-12-24");

        // Save books to the repository
        bookRepository.save(book1);
        bookRepository.save(book2);
    }

    @Test
    public void testFindByAuthor() {
        List<Book> books = bookRepository.findByAuthor("Thanyaluck");
        assertEquals(1, books.size());
        assertEquals("Thanyaluck", books.get(0).getAuthor());
    }

    @Test
    public void testSaveAndRetrieveBook() {
        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
    }
}
