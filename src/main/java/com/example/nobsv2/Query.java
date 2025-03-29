package com.example.nobsv2;

import org.springframework.http.ResponseEntity;

// For GET services
public interface Query<I, O> {
    ResponseEntity<O> execute(I input);
}
