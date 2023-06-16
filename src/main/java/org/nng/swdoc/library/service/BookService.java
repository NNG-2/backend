package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Book;
import org.nng.swdoc.library.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private LibraryService libraryService;


    public Book createBook(Book newBook) {
        Book book = bookRepository.save(newBook);
        logger.info("CRATE book: {}", book.getId());
        return book;
    }

    public Book findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        logger.info("GET book: {}", book.getId());
        return book;
    }

    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        logger.info("GET books: {}", books.size());
        return books;
    }

    public Book updateBook(Book newBook) {
        Book book = bookRepository.findById(newBook.getId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + newBook.getId()));
        newBook.setId(book.getId());
        book = bookRepository.save(newBook);
        logger.info("UPDATE book: {}", book.getId());
        return book;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        logger.info("DELETE book: {}", id);
    }
}

