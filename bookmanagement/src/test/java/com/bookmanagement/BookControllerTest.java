package com.bookmanagement;

import com.bookmanagement.controller.BookController;
import com.bookmanagement.model.Book;
import com.bookmanagement.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetBooksByAuthor() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Thanyaluck");
        book.setPublishedDate("2567-12-24");  // Published date as String (Buddhist date)

        // Mock the service method to return a list of books by author
        when(bookService.getBooksByAuthor("Thanyaluck")).thenReturn(List.of(book));

        // GET request
        mockMvc.perform(get("/books?author=Thanyaluck"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'title':'Test Book','author':'Thanyaluck','publishedDate':'2567-12-24'}]"));
    }

    @Test
    public void testSaveBook() throws Exception {
        // POST request
        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content("{\"title\":\"Test Book\",\"author\":\"Thanyaluck\",\"publishedDate\":\"2567-12-24\"}"))
                .andExpect(status().isCreated());
    }
}
