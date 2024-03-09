package com.example.demo.controller;

import com.example.demo.controller.dto.BookResponse;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.controller.dto.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;


    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> delete(@PathVariable(name = "bookId") Integer bookId,
                            @RequestHeader(name = "USER_ID") Integer userId) {
        service.delete(bookId, userId);
        return ResponseEntity.accepted().build();
    }

    @PostMapping
    public ResponseEntity<BookResponse> save(@RequestHeader(name = "USER_ID") Integer userId,
                                             @RequestBody BookRequest request
    ) {
        return ResponseEntity.ok(service.save(request, userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookResponse>> findAllBooks(@PathVariable(name = "userId") Integer userId) {
        return ResponseEntity.ok(service.findAll(userId));
    }
}
