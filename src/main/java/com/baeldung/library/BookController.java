package com.baeldung.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

//    @GetMapping("/title/${bookTitle}")
//    public List<Book> findBookByTitle(@PathVariable String bookTitle){
//        return bookRepository.findByTitle(bookTitle);
//    }

    @GetMapping("/{id}")
    public Book findOne(Long id){
        return bookRepository.findById(id)
                .orElseThrow();
    }

    @PostMapping(consumes = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bookRepository.findById(id).orElseThrow();
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) throws BookMismatchException {
        if(book.getId() != id) {
            throw new BookMismatchException();
        }
        bookRepository.findById(id).orElseThrow();
        return bookRepository.save(book);
    }

}
