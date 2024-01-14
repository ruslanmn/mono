package ru.kmeansfan.namegen.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
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
    public String name() throws InterruptedException {
        var i = ThreadLocalRandom.current().nextInt(0, NAMES.size());
        var name = NAMES.get(i);

        log.info("{} was generated", name);

        return name;
    }
}
