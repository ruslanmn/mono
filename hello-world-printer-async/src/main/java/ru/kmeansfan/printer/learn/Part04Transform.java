package ru.kmeansfan.printer.learn;


import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kmeansfan.printer.learn.dto.User;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part04Transform {

//========================================================================================

    // TODO Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(
            user -> new User(
                user.getUsername().toUpperCase(),
                user.getFirstname().toUpperCase(),
                user.getLastname().toUpperCase()
            )
        );
    }

//========================================================================================

    // TODO Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(
            user -> new User(
                user.getUsername().toUpperCase(),
                user.getFirstname().toUpperCase(),
                user.getLastname().toUpperCase()
            )
        );
    }

//========================================================================================

    // TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(
            this::asyncCapitalizeUser
        );
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

    public static void main(String[] args) {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
        var nums = Flux.fromStream(IntStream.range(0, 100).boxed())
            .flatMap(i -> Mono.fromSupplier(
                new Supplier<Integer>() {
                    @Override
                    @SneakyThrows
                    public Integer get() {
                        Thread.sleep(100 - i);
                        return i;
                    }
                }
            ), 50);
        nums.subscribe(System.out::println); //todo почему последовательно а не наоборот?
        nums.blockLast();
    }
}
