package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @GetMapping
    public String test(){
        return "test";
    }

    @GetMapping("/exception1")
    public String exception1(){
        throw new NullPointerException();
    }

    @GetMapping("/exception2")
    public String exception2() throws IOException {
        throw new IOException();
    }

    @GetMapping("/exception3")
    public String exception3(){
        throw new IllegalStateException();
    }
}
