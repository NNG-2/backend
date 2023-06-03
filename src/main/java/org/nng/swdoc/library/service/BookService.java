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
        logger.info("Creating book: {}", bookDto);
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        BookDto savedBookDto = bookMapper.toDto(savedBook);
        logger.info("Book created: {}", savedBookDto);
        return savedBookDto;
    }

    public BookDto getBookById(Long id) {
        logger.info("Getting book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        BookDto bookDto = bookMapper.toDto(book);
        logger.info("Retrieved book: {}", bookDto);
        return bookDto;
    }

    public List<BookDto> getAllBooks() {
        logger.info("Getting all books");
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = bookMapper.toDtoList(books);
        logger.info("Retrieved {} books", bookDtos.size());
        return bookDtos;
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        logger.info("Updating book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        bookMapper.updateBookFromDto(bookDto, book);
        Book updatedBook = bookRepository.save(book);
        BookDto updatedBookDto = bookMapper.toDto(updatedBook);
        logger.info("Book updated: {}", updatedBookDto);
        return updatedBookDto;
    }

    public void deleteBook(Long id) {
        logger.info("Deleting book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
        logger.info("Book deleted");
    }
}

