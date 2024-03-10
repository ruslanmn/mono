package ru.kmeansfan.printer.learn;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kmeansfan.printer.learn.dto.User;

import java.time.Duration;

public class Part10ReactiveToBlocking {

    // TODO Return the user contained in that Mono
    User monoToValue(Mono<User> mono) {
        return mono.block();
    }

//========================================================================================

    // TODO Return the users contained in that Flux
    Iterable<User> fluxToValues(Flux<User> flux) {
        return flux.toIterable();
    }

    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(100))
            .take(1000)
            .toIterable(10)
            .forEach(System.out::println);
    }
}
