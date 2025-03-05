package com.baeldung.library;

public class BookNotFoundException  extends RuntimeException{
    public BookNotFoundException(String message, Throwable clause){
        super(message, clause);
    }
}
