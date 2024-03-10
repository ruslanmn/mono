package ru.kmeansfan.printer.learn.repository;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kmeansfan.printer.learn.dto.User;

public class ReactiveUserRepository implements ReactiveRepository<User> {
    @Override
    public Mono<Void> save(Publisher<User> publisher) {
        return Mono.empty();
    }

    @Override
    public Mono<User> findFirst() {
        return Mono.just(User.JESSE);
    }

    @Override
    public Flux<User> findAll() {
        return Flux.just(User.JESSE, User.SKYLER, User.SAUL, User.WALTER);
    }

    @Override
    public Mono<User> findById(String id) {
        return findAll()
            .filter(user -> user.getUsername().equals(id))
            .singleOrEmpty();
    }
}
