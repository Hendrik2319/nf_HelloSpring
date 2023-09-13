package com.example.hellospring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloWorldController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }

    @GetMapping("/hello/{text}")
    public String getHello(@PathVariable String text) {
        return "Hello': %s".formatted(text);
    }

}
