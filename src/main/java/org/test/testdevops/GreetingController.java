package org.test.testdevops;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("before resttemplate");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = Optional.ofNullable(System.getenv("GET_EXTERNAL")).orElse("http://localhost:8080/cudc-rest-testdevops/v1/status");
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response.getBody());
        System.out.println("after resttemplate");
        return "hi";
    }
}