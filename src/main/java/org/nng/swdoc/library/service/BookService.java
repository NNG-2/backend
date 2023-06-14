package org.nng.swdoc.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.nng.swdoc.library.domain.Book;
import org.nng.swdoc.library.dto.BookDto;
import org.nng.swdoc.library.mapper.BookMapper;
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
    private BookMapper bookMapper;

    public BookDto createBook(BookDto bookDto) {
        Book book = bookRepository.save(bookMapper.toEntity(bookDto));
        logger.info("CRATE book: {}", book);
        return bookMapper.toDto(book);
    }

    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        logger.info("GET book: {}", book);
        return bookMapper.toDto(book);
    }

    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        logger.info("GET books: {}", books.size());
        return bookMapper.toDtoList(books);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        bookMapper.updateBookFromDto(bookDto, book);
        book = bookRepository.save(book);
        logger.info("UPDATE book: {}", book);
        return bookMapper.toDto(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        logger.info("DELETE book: {}", id);
    }
}

