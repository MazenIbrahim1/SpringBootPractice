package com.example.nobsv2;

import org.springframework.http.ResponseEntity;

// For services that require body such as POST
public interface Command<I, O> {
    ResponseEntity<O> execute(I input);
}
