package com.example.hellospring;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class HelloWorldController {

    private final List<Message> messages = new ArrayList<>();

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }

    @GetMapping("/hello/{text}")
    public String getHello(@PathVariable String text) {
        return "Hello': %s".formatted(text);
    }

/*
    @PostMapping("/messages")
    public String addMessage(@RequestBody String body) {
        System.out.printf("RequestBody: \"%s\"%n", body);
        return "Ok";
    }
*/

    @PostMapping("/messages")
    public String addMessage(@RequestParam String id, @RequestParam String name, @RequestParam String message) {
        //System.out.printf("RequestParams -> id:\"%s\", name:\"%s\", message:\"%s\"%n", id, name, message);
        Message msg = new Message(id, name, message);
        messages.add(msg);
        return "%s added".formatted(msg);
    }

    @GetMapping("/messages")
    public List<Message> getMessage() {
        return messages;
    }

    @DeleteMapping("/messages/{id}")
    public String deleteMessage(@PathVariable String id) {
        for (int i=0; i<messages.size(); i++) {
            Message message = messages.get(i);
            if (id.equals(message.id())) {
                messages.remove(i);
                return "%s removed".formatted(message);
            }
        }
        return "Can't find message with id \"%s\"".formatted(id);
    }



    @GetMapping("/test")
    public String testBody_Get(@RequestBody String body) {
        return testBody(body, "GET");
    }
    @PostMapping("/test")
    public String testBody_Post(@RequestBody String body) {
        return testBody(body, "POST");
    }
    @PutMapping("/test")
    public String testBody_Put(@RequestBody String body) {
        return testBody(body, "PUT");
    }
    @DeleteMapping("/test")
    public String testBody_Delete(@RequestBody String body) {
        return testBody(body, "DELETE");
    }
    @PatchMapping("/test")
    public String testBody_Patch(@RequestBody String body) {
        return testBody(body, "PATCH");
    }

    public String testBody(String body, String method) {
        System.out.printf("%s request: \"%s\"%n", method, body);
        return "Ok";
    }
}
