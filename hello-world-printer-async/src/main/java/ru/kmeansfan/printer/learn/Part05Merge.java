package ru.kmeansfan.printer.learn;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kmeansfan.printer.learn.dto.User;

import java.time.Duration;
import java.util.stream.IntStream;

public class Part05Merge {
    // TODO Merge flux1 and flux2 values with interleave
    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.mergeWith(flux2);
    }

//========================================================================================

    // TODO Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        return Flux.concat(flux1, flux2);
    }

//========================================================================================

    // TODO Create a Flux containing the value of mono1 then the value of mono2
    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return Flux.concat(mono1, mono2);
    }

    public static void main(String[] args) {
        var nums = Flux.merge(IntStream.range(0, 100)
            .mapToObj(i ->
                Mono.delay(Duration.ofMillis(100 - i)).map(value -> i))
            .toList());
        nums.subscribe(System.out::println);
        nums.blockLast();
    }
}
