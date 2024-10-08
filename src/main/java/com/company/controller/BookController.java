package com.company.controller;

import com.company.model.Book;
import com.company.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository repository;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book " + id + "not found")));
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> myBook = this.repository.findById(id);

        if(myBook.isPresent()) {
            Book bookUpdate = myBook.get();

            bookUpdate.setTitle(book.getTitle());
            bookUpdate.setAuthor(book.getAuthor());
            bookUpdate.setPrice(book.getPrice());
            bookUpdate.setIsbn(book.getIsbn());
            bookUpdate.setPublishedYear(book.getPublishedYear());

            return ResponseEntity.ok(this.repository.save(bookUpdate));

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book " + id + " not found.");
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleNotFound(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }


}
