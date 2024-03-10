package ru.kmeansfan.printer.learn;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kmeansfan.printer.learn.dto.User;

import java.util.Observable;
import java.util.concurrent.Flow;

public class Part07Errors {
    // TODO Return a Mono<User> containing User.SAUL when an error occurs in the input Mono, else do not change the input Mono.
    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
        return mono
            .onErrorReturn(throwable -> throwable instanceof IllegalStateException, User.SAUL);
    }

//========================================================================================

    // TODO Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux, else do not change the input Flux.
    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return flux.onErrorResume(throwable -> Flux.just(User.SAUL, User.JESSE));
    }

//========================================================================================

    // TODO Implement a method that capitalizes each user of the incoming flux using the
    // #capitalizeUser method and emits an error containing a GetOutOfHereException error
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(this::capitalizeUser);
    }

    User capitalizeUser(User user) {
        if (user.equals(User.SAUL)) {
            throw Exceptions.propagate(new GetOutOfHereException());
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {
        private static final long serialVersionUID = 0L;
    }
}
