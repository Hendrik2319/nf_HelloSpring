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


    @GetMapping   ("/test") public String testBody_Get   (@RequestBody String body) {
        return testBody(body, "GET"   );
    }
    @PostMapping  ("/test") public String testBody_Post  (@RequestBody String body) {
        return testBody(body, "POST"  );
    }
    @PutMapping   ("/test") public String testBody_Put   (@RequestBody String body) {
        return testBody(body, "PUT"   );
    }
    @DeleteMapping("/test") public String testBody_Delete(@RequestBody String body) {
        return testBody(body, "DELETE");
    }
    @PatchMapping ("/test") public String testBody_Patch (@RequestBody String body) { return testBody(body, "PATCH" ); }

    public String testBody(String body, String method) {
        String response = "%s request: \"%s\"".formatted(method, body);
        System.out.println(response);
        return response;
    }


    @GetMapping("/form")
    public String testForm_Get(@RequestParam String id, @RequestParam String name, @RequestParam String message) {
        return testForm(id, name, message, "GET");
    }
    @PostMapping("/form")
    public String testForm_Post(@RequestParam String id, @RequestParam String name, @RequestParam String message) {
        return testForm(id, name, message, "POST");
    }
    @PutMapping("/form")
    public String testForm_Put(@RequestParam String id, @RequestParam String name, @RequestParam String message) {
        return testForm(id, name, message, "PUT");
    }
    @DeleteMapping("/form")
    public String testForm_Delete(@RequestParam String id, @RequestParam String name, @RequestParam String message) {
        return testForm(id, name, message, "DELETE");
    }
    @PatchMapping("/form")
    public String testForm_Patch(@RequestParam String id, @RequestParam String name, @RequestParam String message) {
        return testForm(id, name, message, "PATCH");
    }

    public String testForm(String id, String name, String message, String method) {
        String response = "%s request -> id:\"%s\", name:\"%s\", message:\"%s\"".formatted(method, id, name, message);
        System.out.println(response);
        return response;
    }


    @GetMapping   ("/json") public String testJSON_Get   (@RequestBody Message message) { return testJSON(message, "GET"   ); }
    @PostMapping  ("/json") public String testJSON_Post  (@RequestBody Message message) { return testJSON(message, "POST"  ); }
    @PutMapping   ("/json") public String testJSON_Put   (@RequestBody Message message) { return testJSON(message, "PUT"   ); }
    @DeleteMapping("/json") public String testJSON_Delete(@RequestBody Message message) { return testJSON(message, "DELETE"); }
    @PatchMapping ("/json") public String testJSON_Patch (@RequestBody Message message) { return testJSON(message, "PATCH" ); }

    public String testJSON(Message message, String method) {
        String response = "%s request -> %s".formatted(method, message);
        System.out.println(response);
        return response;
    }
}
