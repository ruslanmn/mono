package ru.kmeansfan.printer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/print")
public class PrintController {
    private final RestTemplate restTemplate;

    @GetMapping
    public String print() {
        return "Hello, " + restTemplate.getForObject(
            "http://localhost:8080/api/name",
            String.class
        );
    }
}
