package ru.kmeansfan.printer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PrintController {
    private final WebClient nameWebClient;

    @GetMapping("/print")
    public Mono<String> print() {
        return nameWebClient.get()
            .uri("/api/name")
            .exchangeToMono(clientResponse ->
                clientResponse.bodyToMono(String.class))
            .map(name -> "Hello, " + name)
            .doOnNext(log::info)
            .doOnError(throwable -> log.error("Failed to generate name", throwable));
    }

    @GetMapping("/log")
    public void log() {
        var flux = nameWebClient.get()
            .uri("/api/name")
            .exchangeToMono(clientResponse ->
                clientResponse.bodyToMono(String.class))
            .map(name -> "Hello, " + name);
        flux.subscribe(log::info);
        flux.subscribe(log::info);
    }
}
