package ru.kmeansfan.module1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/name")
public class NameController {
    private final List<String> NAMES = List.of(
        "Ruslan",
        "Mikhail",
        "Victor",
        "Artem",
        "Dmitry",
        "Oleg",
        "Igor",
        "Egor"
    );

    @GetMapping
    public String name() {
        var i = ThreadLocalRandom.current().nextInt(0, NAMES.size());
        return NAMES.get(i);
    }
}
