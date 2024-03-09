package com.example.demo.service;

import com.example.demo.controller.dto.BookResponse;
import com.example.demo.enums.Role;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.controller.dto.BookRequest;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void delete(Integer bookId, Integer userId) {
        User user = userRepository.findUserById(userId).orElseThrow(()
                -> new IllegalStateException("Cannot find user by provided id"));


        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new IllegalStateException("Cannot find book by provided id"));

        if (Role.ADMIN.equals(user.getRole())) {
            bookRepository.deleteById(bookId);
        } else {
            if (!book.getUser().getId().equals(user.getId())) {
                throw new IllegalStateException("You are not allowed to delete the book, which you dont own");
            }
            bookRepository.deleteById(bookId);
        }
    }

    @Transactional
    public BookResponse save(BookRequest request, Integer userId) {
        User user = userRepository.findUserById(userId).orElseThrow(()
                -> new IllegalStateException("Cannot find user by provided id"));

        Book book = Book.builder()
                        .author(request.getAuthor())
                        .isbn(request.getIsbn())
                        .user(user)
                        .createDate(LocalDateTime.now())
                        .lastModified(LocalDateTime.now())
                        .build();
        bookRepository.save(book);

        return mapToBookResponse(book);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> findAll(Integer userId) {
        User user = userRepository.findUserById(userId).orElseThrow(()
                -> new IllegalStateException("Cannot find user by provided id"));
        if (Role.ADMIN.equals(user.getRole())) {
            return mapToBookResponseList(bookRepository.findAll());
        } else {
            return mapToBookResponseList(bookRepository.findAllByUser(user));
        }
    }

    private List<BookResponse> mapToBookResponseList(List<Book> books) {
        return books.stream().map(this::mapToBookResponse).collect(Collectors.toList());
    }

    private BookResponse mapToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                           .build();
    }
}
