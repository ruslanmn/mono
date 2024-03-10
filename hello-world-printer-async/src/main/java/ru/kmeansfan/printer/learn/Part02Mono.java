package ru.kmeansfan.printer.learn;

import reactor.core.publisher.Mono;

import java.time.Duration;

public class Part02Mono {

//========================================================================================

    // TODO Return an empty Mono
    Mono<String> emptyMono() {
        return Mono.empty();
    }

//========================================================================================

    // TODO Return a Mono that never emits any signal
    Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

//========================================================================================

    // TODO Return a Mono that contains a "foo" value
    Mono<String> fooMono() {
        return Mono.just("foo");
    }

//========================================================================================

    // TODO Create a Mono that emits an IllegalStateException
    Mono<String> errorMono() {
        return Mono.error(IllegalStateException::new);
    }

    public static void main(String[] args) {
        System.out.println(
            Mono.just("helllo")
                .block(Duration.ofMillis(300))
        );
    }

}
