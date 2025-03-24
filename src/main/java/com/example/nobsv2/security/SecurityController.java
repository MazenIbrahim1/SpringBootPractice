package com.example.nobsv2.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/open")
    public String open() {
        return "OPEN";
    }

    @GetMapping("/closed")
    public String closed() {
        return "CLOSED";
    }
}
